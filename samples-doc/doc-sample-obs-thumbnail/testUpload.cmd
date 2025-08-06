REM ########################################################################
REM Sample to upload picture to obs bucket using Huawei obsutil.
REM Huawei obsutil is available, see:
REM https://support.huaweicloud.com/intl/en-us/utiltg-obs/obs_11_0001.html
REM ########################################################################

REM for proxy use, set following environment variables
REM set HTTP_PROXY=proxy:port
REM set HTTPS_PROXY=proxy:port

obsutil.exe cp .\src\test\resources\otc.jpg ^
  obs://doc-sample-obs-thumbnail-createthumbnails-images/otc.jpg ^
  -e=https://obs.eu-de.otc.t-systems.com ^
  -i=%ACCESS_KEY% ^
  -k=%SECRET_ACCESS_KEY%
