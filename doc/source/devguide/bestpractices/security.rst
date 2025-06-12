FunctionGraph Security Best Practices
=====================================

Security is a shared responsibility between OpenTelekomCloud and you. 
OpenTelekomCloud is responsible for the security of the cloud service itself and provides a secure cloud environment. 
You need to reasonably use the security features provided by the cloud service to protect your data and ensure the 
safe use of the cloud service. For more information, please see Shared Responsibility .

This article provides the best security practices for using FunctionGraph, 
aiming to provide actionable and normative guidance to improve overall security capabilities. 
Based on this guidance document, you can continuously evaluate the security status of functions, 
effectively combine the various security features provided by FunctionGraph, enhance the overall 
security defense capabilities of FunctionGraph, ensure that data stored in FunctionGraph is not 
leaked or tampered with, and ensure the security of data transmission.

This article provides suggestions from the following dimensions, which you can use to evaluate 
the use of FunctionGraph and perform security configuration based on this basis according to business needs.

Use trusted code and dependencies to avoid code vulnerabilities
---------------------------------------------------------------

* Before deploying function code, we recommend that you perform static scanning and vulnerability 
  analysis on the code to ensure code security.
  
* Use dependent libraries from reliable sources and update them regularly, and avoid using 
  third-party libraries with known vulnerabilities.


Protect sensitive information and prevent leakage of sensitive information
---------------------------------------------------------------------------

* If the user code or configuration contains sensitive information, such as AK/SK, token, password, etc., 
  it is strongly recommended to use encrypted environment variables . 
  Otherwise, this information may be displayed in plain text in the user interface or API return results, 
  resulting in sensitive information leakage.

* For data involving user privacy (logs, personal information, etc.), it is recommended to perform desensitization during 
  function processing and not print it in plain text through logs to prevent sensitive information leakage.

* FunctionGraph can provide users with temporary codes and download addresses and set validity periods. 
  Users should prevent temporary download addresses from being leaked to reduce the risk of code or library leaks.

Fine-tune permission control and enable identity authentication
---------------------------------------------------------------

* When configuring delegation and AK/SK for FunctionGraph functions through OpenTelekomCloud's 
  unified identity authentication service (IAM), you should follow the principle of least privilege 
  to ensure that the function can only access specified resources. For example, limit the read and 
  write permissions of the function to a specific OBS bucket to prevent unauthorized access.

* When configuring an APIG trigger, it is recommended to enable IAM authentication or custom authentication 
  to ensure that only authorized requests can trigger function execution. 
  In addition, traffic control can be implemented through APIG to prevent malicious requests from causing resource exhaustion.

* Configure VPC for functions to prevent external attacks
  When a user function needs to access resources in OpenTelekomCloud Virtual Private Cloud (VPC), such as RDS, 
  it is recommended to configure VPC for the function to ensure that communication between the function and other 
  cloud services is carried out in an isolated network environment.

Use function version control to quickly update and rollback
-----------------------------------------------------------

FunctionGraph supports version management of functions. It is recommended to create several versions for each 
function and use the stable version in the production environment. 
At the same time, through the alias function, the function of the specified version is associated to 
achieve version switching to ensure that it can be quickly rolled back when a security problem occurs.
