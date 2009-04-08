var equipArray = ["常用设备", "工艺大厅设备", "物化设备", "方向托管设备"];

var equipArray2 = new Array();
for (var i = 1; i < equipArray.length; i++) {
	var arr = new Array();
	arr[0] = i;
	arr[1] = equipArray[i];
	equipArray2[i - 1] = arr;
}
var groupArray = new Array();
for (var i = 1; i < equipArray.length; i++) {
	var arr = new Array();
	arr[0] = i;
	arr[1] = equipArray[i];
	groupArray[i - 1] = arr;
}
groupArray.push([0, "所有设备"])

var equipColumns = [['name', '设备名称'], ['model', '型号'], ['no', '设备编号'],
		['specification', '性能参数'], ['admin', '负责人'], ['mobile', '联系方式'],
		['country', '国别'], ['company', '生产厂商'], ['location', '存放位置'],
		['caution', '操作规程'], ['remark', '收费方式']];

var themeArray = [['预设蓝色主题', 'resources/css/ext-all.css'],
		['灰色主题', 'resources/css/xtheme-gray.css'],
		['深灰色主题', 'resources/css/xtheme-darkgray.css'],
		['黑色主题', 'resources/css/xtheme-slickness.css'],
		['暗蓝色主题', 'resources/css/xtheme-slate.css'],
		['橄榄主题', 'resources/css/xtheme-olive.css'],
		['紫色主题', 'resources/css/xtheme-purple.css']];