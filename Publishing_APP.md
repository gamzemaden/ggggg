# Publishing APP #

怎么样发布Android APP:
  1. 去掉程序里的Log语句
  1. 在AndroidManifest.xml里关掉 debugging，选择最低支持版本的SDK版本，设置好code version和code name
  1. 测试APP
  1. 签名和build APK。 在AndroidManifest里选择Use the Export Wizard。这个certificate对应这
> > 个application，上传app需要这个certificate。要保存好。
  1. 准备图片
    * highRes icon: on the android market in the web. 512x512
    * promo: on the android market on mobile device.
    * feature: on the android market in the web.  1024x500
    * 软件的截图
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Graphics_2D/