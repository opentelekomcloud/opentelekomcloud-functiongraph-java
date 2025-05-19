import os
import boto3
from botocore.client import Config
from pathlib import Path
import mimetypes

def get_s3_client():
    """Create a boto3 client for OBS using the S3 interface."""
    return boto3.client(
        's3',
        region_name='eu-de',
        endpoint_url='https://obs.eu-de.otc.t-systems.com',
        aws_access_key_id=os.getenv('AWS_ACCESS_KEY_ID'),
        aws_secret_access_key=os.getenv('AWS_SECRET_ACCESS_KEY'),
        config=Config(s3={'addressing_style': 'path'})
    )

def list_objects(s3, bucket, prefix):
    """List all objects under a prefix."""
    paginator = s3.get_paginator('list_objects_v2')
    pages = paginator.paginate(Bucket=bucket, Prefix=prefix)
    for page in pages:
        for obj in page.get('Contents', []):
            yield obj['Key']

def delete_prefix(s3, bucket, prefix):
    """Delete all objects under a prefix."""
    print(f"Deleting all objects under: {prefix}")
    to_delete = [{'Key': key} for key in list_objects(s3, bucket, prefix)]
    if not to_delete:
        print("No objects to delete.")
        return

    for i in range(0, len(to_delete), 1000):
        chunk = to_delete[i:i+1000]
        s3.delete_objects(Bucket=bucket, Delete={'Objects': chunk})
        print(f"Deleted {len(chunk)} objects.")

def upload_directory(s3, bucket, local_path, remote_prefix):
    """Recursively upload a local directory to S3 with correct Content-Type."""
    local_path = Path(local_path)
    for file_path in local_path.rglob('*'):
        if file_path.is_file():
            relative_path = file_path.relative_to(local_path)
            s3_key = f"{remote_prefix}{relative_path.as_posix()}"
            content_type, _ = mimetypes.guess_type(file_path.as_posix())
            content_type = content_type or 'application/octet-stream'

            print(f"Uploading {file_path} to {s3_key} with Content-Type: {content_type}")
            s3.upload_file(
                str(file_path),
                bucket,
                s3_key,
                ExtraArgs={'ContentType': content_type}
            )


def main():
    # Resolve reference name
    ref_name = os.getenv('GITHUB_HEAD_REF') or os.getenv('GITHUB_REF_NAME') or 'default-branch'
    remote_prefix = f"opentelekomcloud-functiongraph-java/{ref_name}/"
    bucket = 'helpcenter-docs'
    local_dir = 'doc/build/html'

    # Create client
    s3 = get_s3_client()

    # Delete old artifacts
    delete_prefix(s3, bucket, remote_prefix)

    # Upload new artifacts
    upload_directory(s3, bucket, local_dir, remote_prefix)

if __name__ == "__main__":
    main()
