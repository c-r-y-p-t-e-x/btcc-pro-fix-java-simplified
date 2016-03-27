# BTCC Pro Exchange FIX Java Client (Simplified Version)
###1.配置quickfix-client.properties

SenderCompID 使用您的唯一的AccessKey

在[keys]下配置您的AccessKey 和 SecretKey 格式如下：

[keys]
AccessKey=您的访问秘钥
SecretKey=您的秘密秘钥

###2.使用方法
发送请求的入口在App.java 中，每次只能发送一个请求(TestMessage)。在请求中输入参数，注释掉其他的请求，点击运行就可以发出。可以通过调整MessageProvider.java来改变发送请求的参数。

###3.验证方法在GenAccountString.java中


###4.注意：
BTCC PRO FIX 用的是自定义的FIX 4.4 协议，在本示例代码中是selfFIX44.xml文件，最后一次更新在2016年3月22日，请用户及时更新您的文件。


