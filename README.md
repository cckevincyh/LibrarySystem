## SSH图书管理系统


本系统前台利用BootStrap框架搭建UI界面,利用AJAX技术异步请求后台，后台使用SSH框架+ Quartz框架+JSP+ MySQL数据库构建网站，此系统分为前台管理和后台管理。管理员可以维护图书信息、图书分类信息、读者信息、管理员信息、借阅管理，归还管理，逾期处理，借阅规则设置，修改管理员个人资料、密码等；读者可以修改个人信息、修改密码，查看借阅信息，归还信息，逾期信息等等。游客无需登录就可以查询本系统的图书信息。

### 概述

图书馆管理系统，是一个基于 Web 的 B/S 系统,面向学校、图书馆等部门的书籍管理、浏览和发布系统，通过将海量资源、信息管理和网络发布系统的进行有机结合，不仅能够充分满足学生对知识的渴求，充实学校的教育资源，而且不受时间和空间限制，让学生随时随地地获取知识,所以图书馆管理系统的应用要达到能快速查找到书籍的索书号，能查询图书的借阅情况等目的。能够方便读者借阅图书。
在图书管理系统中，管理员为每个读者建立一个账户，账户内存储读者个人的详细信息，。读者可以在图书馆进行图书的借、还、续借、查询等操作，不同类别的读者在借书限额、还书期限以及逾期罚金上要有所不同。
借阅图书时，由管理员录入读者证件号，图书编号，由学生输入密码，系统首先验证该学生的密码是否正确，不正确则会提示密码错误,然后还会检验该读者的借阅是否已达到上限，该书是否是馆内最后一本，是否可借阅。完成借书操作的同时要修改相应图书信息的状态、在借阅信息中添加相应的记录。
归还图书时，由管理员录入借书读者证件号和待归还的图书编号，显示读者证件号、读者姓名、读书编号、读书名称、借书日期、应还日期等信息，若进行续借则取消超期和罚款等信息。完成归还操作的同时，修改相应图书信息的状态、修改读者信息中的已借数量、在借书信息中对相应的借书记录做标记、在还书信息中添加相应的记录。
图书管理员不定期地对图书信息进行添加、修改和删除等操作，在图书尚未归还的情况下不能对图书信息进行删除。也可以对读者信息进行添加、修改、删除等操作，在读者还有未归还的图书的情况下不能进行删除读者信息。
超级系统管理员处理进行读者类别信息的设置、读者管理设置，图书类别的设置，图书信息管理设置，罚金设置还可以进行图书管理员权限的设置、对普通图书管理员进行设置。


### 功能划分

通过对用户需求的分析，可以分析出该网上购物系统大致可以分六个功能模块：读者管理模块、图书类别管理模块、图书管理模块、借阅管理模块，管理员管理模块，系统设置模块。 


![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/0.png)


### 界面设计


- 管理员登录界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/1.png)

- 读者登录界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/2.png)



- 管理员主界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/3.png)


- 管理员个人资料界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/4.png)


- 管理员密码修改界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/9.png)

- 管理员读者管理界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/5.png)


- 管理员读者添加界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/12.png)


- 超级管理员管理普通管理员界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/6.png)

- 超级管理员添加普通管理员界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/10.png)

- 超级管理员对普通管理员进行权限管理界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/11.png)

- 管理员图书管理界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/7.png)

- 管理员图书添加界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/17.png)


- 管理员图书分类管理界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/8.png)


- 管理员图书分类添加界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/16.png)


- 管理员借阅管理界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/13.png)


- 管理员归还管理界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/14.png)


- 管理员借阅查询界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/15.png)


- 管理员逾期管理界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/20.png)

- 管理员系统设置界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/18.png)


- 读者界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/19.png)


- 读者个人借阅查询界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/21.png)


- 读者借书之后的个人借阅查询界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/21.png)


- 读者还书之后的个人借阅查询界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/22.png)


- 游客界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/23.png)


- 游客图书查询界面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/24.png)



- 404页面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/25.png)


- 500页面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/26.png)


- 权限不通过页面

![image](https://github.com/cckevincyh/LibrarySystem/blob/master/img/27.png)

