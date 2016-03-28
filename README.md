# BTCC Pro Exchange FIX Java Client (Simplified Version)

##English 
###1.How to config  quickfix-client.properties?

SenderCompID=<Your AccessKey>
[Keys]
AccessKey=<Your AccessKey>
SecertKey=<Your SecertKey>

###2. How to use the demo code.

You only need to run App.java. 
There are 7 requests in the main function.You can only send one request one time and comment other requests.
You can change the parameters of the requests in MessageProvider.java file.

###3.Note:

BTCC PRO FIX use self defination FIX 4.4 protocol.Please update your FIX44.xml if your version is before March 22th 2016 same as the selfFIX44.xml in the demo.

##中文

###1.配置quickfix-client.properties

SenderCompID 使用您的唯一的AccessKey

在[keys]下配置您的AccessKey 和 SecretKey 格式如下：

[keys]
AccessKey=您的访问秘钥
SecretKey=您的秘密秘钥

###2.使用方法
发送请求的入口在App.java 中，每次只能发送一个请求(TestMessage)。在请求中输入参数，注释掉其他的请求，点击运行就可以发出。可以通过调整MessageProvider.java来改变发送请求的参数。

###3.注意：
BTCC PRO FIX 用的是自定义的FIX 4.4 协议，在本示例代码中是selfFIX44.xml文件，最后一次更新在2016年3月22日，请用户及时更新您的文件。


