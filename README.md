# tess4j
本程序基于java和tesseract-ocr，使用tess4j和hssf实现对文件夹中的天猫企业工商信息图片进行文字提取并保存到excel表格
实现思路如下：
1.对文件夹中的图片进行批量处理，去除水印并截取上半部分，保存到文件夹
2.对处理后的图片使用tesseract进行文字识别，识别出图片中的企业名称和企业注册号
3.把提取出的信息输入到一个excel表格中

newone文件夹为eclipse工程文件夹
用户可自行修改程序中的文件夹路径，使其指向自己所要识别或保存的文件夹
本程序使用的tessdata字库训练较少，中文识别不太精确，用户可自行训练字库并使用以提高精确度

本程序是对中国软件杯题目实现，题目如下：
![image1](https://github.com/passergentleman/tess4j/blob/master/photo/1.png)

图片未处理前效果：
![image2](https://github.com/passergentleman/tess4j/blob/master/photo/2.png)

图片处理后效果：
![image3](https://github.com/passergentleman/tess4j/blob/master/photo/3.png)

识别后excel表格内容：
![image4](https://github.com/passergentleman/tess4j/blob/master/photo/4.png)
