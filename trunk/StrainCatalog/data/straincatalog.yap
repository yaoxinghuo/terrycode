db4o                                 �  �   1  �      A          v   s      �                     �      Z   v   R   �   �  <   j      �   �    �                 �                       c o m . d b 4 o . e x t . D b 4 o D a t a b a s e            k      i _ s i g n a t u r e           i _ u u i d          Z   G����             )  (OV�̈�k�   !Terry-PC457e01284f56eaccdc7fc7950  �      �             R �'�uf                       R      v   R  �                               v  �  �   l  �     �     �       c o m . d b 4 o . S t a t i c F i e l d            9      n a m e    	        v a l u e           N   Tv  "  5   �  	! ҙ�  �  �  .  _      v  �    c o m . d b 4 o . S t a t i c C l a s s                 n a m e    	        f i e l d s    �       
     �     �     :      N     �      {     �      �      �   n  �           $c o m . t e r r y . s t r a i n c a t a l o g . m o d e l . S t r a i n           2      i d    	        c h i n e s e N a m e    	        	l a t i n N a m e    	        s t r a i n S o u r c e s    	        r e m a r k    	        s t r a i n D e s c r i b e    	        c u l t u r e M e d u i m    	        a c t i v a t i o n M e t h o d    	        p h y s i c a l N u m b e r    	        a c c e s s i o n N u m b e r    	        p r e s e n t L o c a t i o n    	        p r e s e r v a t i o n    	        i d e n t i f i c a t i o n M e t h o d    	        i d e n t i f i c a t i o n T i m e    	     ���<             L        �      �      �      �   
   �      �      �      �      �      �   
   �   
         �T   2 2 2    �_ǏVY   �T   SbR�~�g   0R�N�fw1\   0R�N�fw   d f ; l g k    s d g    0W�eT�~   �v�ؚ�y�b   d s k j g    1 a b c    $7 a 3 3 0 f 8 3 - 8 d 2 7 - 4 d 1 6 - 9 c 2 b - 1 c a d e e 9 0 8 0 5 e       �   �    �  �                 �      v  "�  .  _   (c o m . t e r r y . s t r a i n c a t a l o g . m o d e l . A t t a c h m e n t           *       (c o m . t e r r y . s t r a i n c a t a l o g . m o d e l . A t t a c h m e n t           *      i d    	        s t r a i n I d    	        f i l e N a m e    	        d a t a           d e s c r i p t i o n    	     ���M        �M   L �   L ��      B ѧ   6      t e s t ��k� џ<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>jQuery 1.3 API 参考文档中文版</title>
<meta name="keywords" content="jQuery 1.3 API Reference,Visual jQuery 1.3,jQuery doc,jQuery API,jQuery,jQuery 1.2 API 参考文档中文版,中文版" />
<meta name="description" content="jQuery 1.3 API 文档中文版，提供最新版 jQuery 开发帮助。" />
<link rel="stylesheet" type="text/css" href="style/jqueryapi.css" />
<script type="text/javascript" src="style/lib/jquery.js"></script>
<script type="text/javascript" src="style/lib/jquery.dimensions.js"></script>
<script type="text/javascript" src="style/lib/jquery.corner-min.js"></script>
<script type="text/javascript" src="style/jqueryapi.js"></script>
<link rel="shortcut icon" href="style/img/favicon.ico" />
</head>
<body>
<div id="topnav">
	<a class="topnav" href="http://cloudream.name/" title="1.2+版翻译协助者">Cloudream</a>
	<span class="topnav">|</span>
	<a class="topnav" href="http://mrwlwan.wordpress.com/" title="1.2版翻译样式作者">Ross Wan</a>
	<span class="topnav">|</span>
	<a class="topnav" href="http://www.cn-cuckoo.com/" title="1.1版译者">为之漫笔</a>
	<span class="topnav">|</span>
	<a class="topnav" href="http://shawphy.com/" title="1.2+版翻译整理者">Shawphy</a>
	<span class="topnav">|</span>
	<a class="topnav" href="http://code.google.com/p/jquery-api-zh-cn/downloads/list" title="1.3离线版下载">jQuery 1.3 API 离线版下载</a>
</div>
<div id="header"></div>
<hr style="color:#595959;" />
<div id="foldandexpand">
	<button id="fold">折叠</button>
	<button id="expand">展开</button>
	<button id="foldall">折叠全部</button>
	<button id="expandall">展开全部</button>
	<input id="english" name="english" type="checkbox" value="" /><label for="english"> 英文说明</label>
</div>
<div class="mainmenu">
	<!-- Core -->
	<div class="menuitem">核心</div>
	<div class="functionmenu">
		<div class="categoryitem">jQuery 核心函数</div>
		<div class="category">
			<div class="functionitem">jQuery(expression,[context])</div>
			<div class="content">
				<h1>jQuery(expression,[context])</h1>
				<div class="desc">
					<div>这个函数接收一个包含 CSS 选择器的字符串，然后用这个字符串去匹配一组元素。
					</div>
					<div class="longdesc"><p>jQuery 的核心功能都是通过这个函数实现的。 jQuery中的一切都基于这个函数，或者说都是在以某种方式使用这个函数。这个函数最基本的用法就是向它传递一个表达式（通常由 CSS 选择器组成），然后根据这个表达式来查找所有匹配的元素。</p><p>默认情况下, 如果没有指定context参数，$()将在当前的 HTML 文档中查找 DOM 元素；如果指定了 context 参数，如一个 DOM 元素集或 jQuery 对象，那就会在这个 context 中查找。</p><p>参考 Selectors 获取更多用于 expression 参数的 CSS 语法的信息。</p>
					</div>
					<hr />
					<div>This function accepts a string containing a CSS selector which is then used to match a set of elements.
					</div>
					<div class="longdesc"><p>The core functionality of jQuery centers around this function. Everything in jQuery is based upon this, or uses this in some way. The most basic use of this function is to pass in an expression (usually consisting of CSS), which then finds all matching elements. </p><p>By default, if no context is specified, $() looks for DOM elements within the context of the current HTML document. If you do specify a context, such as a DOM element or jQuery object, the expression will be matched against the contents of that context. </p><p>See Selectors for the allowed CSS syntax for expressions.</p>
					</div>
				</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expression </strong>(String) : 用来查找的字符串</p>
				<p class="indent"><strong>context </strong>(Element, jQuery) : (可选) 作为待查找的 DOM 元素集、文档或 jQuery 对象。</p>
				<h2>示例</h2>
				<p class="indent">
					找到所有 p 元素，并且这些元素都必须是 div 元素的子元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;one&lt;/p&gt; &lt;div&gt;&lt;p&gt;two&lt;/p&gt;&lt;/div&gt; &lt;p&gt;three&lt;/p&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div &gt; p");
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;two&lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					在文档的第一个表单中，查找所有的单选按钮(即: type 值为 radio 的 input 元素)。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input:radio", document.forms[0]);
				</div>
				<hr />
				<p class="indent">
					在一个由 AJAX 返回的 XML 文档中，查找所有的 div 元素。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div", xml.responseXML);
				</div>
			</div>
			<div class="functionitem">jQuery(html,[ownerDocument])</div>
			<div class="content">
				<h1>jQuery(html,[ownerDocument])</h1>
				<div class="desc">
					<div>根据提供的原始 HTML 标记字符串，动态创建由 jQuery 对象包装的 DOM 元素。
					</div>
					<div class="longdesc">你可以传递一个手写的 HTML 字符串，或者由某些模板引擎或插件创建的字符串，也可以是通过 AJAX 加载过来的字符串。但是在你创建 input 元素的时会有限制，可以参考第二个示例。当然这个字符串可以包含斜杠 (比如一个图像地址)，还有反斜杠。当你创建单个元素时，请使用闭合标签或 XHTML 格式。例如，创建一个 span ，可以用 $("&lt;span/&gt;") 或 $("&lt;span&gt;&lt;/span&gt;") ，但不推荐 $("&lt;span&gt;")。在jQuery 中，这个语法等同于$(document.createElement("span")) 。
					</div>
					<hr />
					<div>Create DOM elements on-the-fly from the provided String of raw HTML.
					</div>
					<div class="longdesc">You can pass in plain HTML Strings written by hand, create them using some template engine or plugin, or load them via AJAX. There are limitations when creating input elements, see the second example. Also when passing strings that may include slashes (such as an image path), escape the slashes. When creating single elements use the closing tag or XHTML format. For example, to create a span use $("&lt;span/&gt;") or $("&lt;span&gt;&lt;/span&gt;") instead of without the closing slash/tag. As of jQuery 1.3 this syntax is completely equivalent to $(document.createElement("span"))</div>
				</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>html </strong>(String) : 用于动态创建DOM元素的HTML标记字符串</p>
				<p class="indent"><strong>ownerDocument </strong>(Document) : 可选，创建DOM元素所在的文档</p>
				<h2>示例</h2>
				<p class="indent">
					动态创建一个 div 元素（以及其中的所有内容），并将它追加到 body 元素中。在这个函数的内部，是通过临时创建一个元素，并将这个元素的 innerHTML 属性设置为给定的标记字符串，来实现标记到 DOM 元素转换的。所以，这个函数既有灵活性，也有局限性。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("&lt;div&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;/div&gt;").appendTo("body");
				</div>
				<hr />
				<p class="indent">
				创建一个 &lt;input&gt; 元素必须同时设定 type 属性。因为微软规定 &lt;input&gt; 元素的 type 只能写一次。</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
				// 在 IE 中无效:<br/>
$("&lt;input&gt;").attr("type", "checkbox");<br/>
// 在 IE 中有效:<br/>
$("&lt;input type='checkbox'&gt;");<br/>
				</div>
			</div>
			<div class="functionitem">jQuery(elements)</div>
			<div class="content">
				<h1>jQuery(elements)</h1>
				<div class="desc"><div>将一个或多个DOM元素转化为jQuery对象。</div>
				<div class="longdesc">这个函数也可以接收XML文档和Window对象（虽然它们不是DOM元素）作为有效的参数。</div>
				<hr />
				<div>Wrap jQuery functionality around a single or multiple DOM Element(s).</div>
				<div class="longdesc">This function also accepts XML Documents and Window objects as valid arguments (even though they are not DOM Elements).</div>
				</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>elements </strong>(Element, Array&lt;Element&gt;) : 用于封装成jQuery对象的DOM元素</p>
				<h2>示例</h2>
				<p class="indent">
					设置页面背景色。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(document.body).css( "background", "black" );
				</div>
				<hr />
				<p class="indent">
					隐藏一个表单中所有元素。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(myForm.elements).hide()
				</div>
			</div>
			<div class="functionitem">jQuery(callback)</div>
			<div class="content">
				<h1>jQuery(callback)</h1>
			   <div class="desc">
			   <div>$(document).ready()的简写。</div>
			   <div class="longdesc"><p>允许你绑定一个在DOM文档载入完成后执行的函数。这个函数的作用如同$(document).ready()一样，只不过用这个函数时，需要把页面中所有需要在 DOM 加载完成时执行的$()操作符都包装到其中来。从技术上来说，这个函数是可链接的－－但真正以这种方式链接的情况并不多。</p><p>你可以在一个页面中使用任意多个$(document).ready事件。</p>参考 ready(Function) 获取更多 ready 事件的信息。</div>
			   <hr />
			   <div>A shorthand for $(document).ready().</div>
			   <div class="longdesc">Allows you to bind a function to be executed when the DOM document has finished loading. This function behaves just like $(document).ready(), in that it should be used to wrap other $() operations on your page that depend on the DOM being ready to be operated on. While this function is, technically, chainable - there really isn't much use for chaining against it. <p>You can have as many $(document).ready events on your page as you like. </p> <p>See ready(Function) for details about the ready event.</p></div>
			   </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 当DOM加载完成后要执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					当DOM加载完成后，执行其中的函数。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(function(){<br/>
  // 文档就绪<br/>
});
				</div>
				<hr />
				<p class="indent">
					使用 $(document).ready() 的简写，同时内部的 jQuery 代码依然使用 $ 作为别名，而不管全局的 $ 为何。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					jQuery(function($) {<br/>
  // 你可以在这里继续使用$作为别名...<br/>
});
				</div>
			</div>
		</div>
		<div class="categoryitem">jQuery 对象访问</div>
		<div class="category">
			<div class="functionitem">each(callback)</div>
			<div class="content">
				<h1>each(callback)</h1>
				<div class="desc"><div>以每一个匹配的元素作为上下文来执行一个函数。</div> <div class="longdesc">意味着，每次执行传递进来的函数时，函数中的this关键字都指向一个不同的DOM元素（每次都是一个不同的匹配元素）。<p>而且，在每次执行函数时，都会给函数传递一个表示作为执行环境的元素在匹配的元素集合中所处位置的数字值作为参数（从零开始的整形）。 </p>返回 'false' 将停止循环 (就像在普通的循环中使用 'break')。返回 'true' 跳至下一个循环(就像在普通的循环中使用'continue')。</div><hr />
				<div>Execute a function within the context of every matched element.</div> <div class="longdesc">This means that every time the passed-in function is executed (which is once for every element matched) the 'this' keyword points to the specific DOM element. <p>Additionally, the function, when executed, is passed a single argument representing the position of the element in the matched set (integer, zero-index). </p>Returning 'false' from within the each function completely stops the loop through all of the elements (this is like using a 'break' with a normal loop). Returning 'true' from within the loop skips to the next iteration (this is like using a 'continue' with a normal loop).</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 对于每个匹配的元素所要执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					迭代两个图像，并设置它们的 src 属性。注意:此处 this 指代的是 DOM 对象而非 jQuery 对象。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;img/&gt;&lt;img/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").each(function(i){<br/>
&nbsp;&nbsp; this.src = "test" + i + ".jpg";<br/>
&nbsp;});
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;img src="test0.jpg" /&gt;, &lt;img src="test1.jpg" /&gt; ] 
				</div>
				<hr />
				<p class="indent">
					如果你想得到 jQuery对象，可以使用 $(this) 函数。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").each(function(){<br/>
&nbsp; $(this).toggleClass("example");<br/>
});
				</div>
				<hr />
				<p class="indent">
					你可以使用 'return' 来提前跳出 each() 循环。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
<pre>
&lt;button>Change colors&lt;/button>
&lt;span&gt;&lt;/span&gt; 
&lt;div&gt;&lt;/div&gt; 
&lt;div&gt;&lt;/div&gt;

&lt;div>&lt;/div&gt; 
&lt;div>&lt;/div&gt;
&lt;div id="stop"&gt;Stop here&lt;/div&gt; 
&lt;div&gt;&lt;/div&gt;

&lt;div&gt;&lt;/div&gt;
&lt;div&gt;&lt;/div&gt;
</pre>                </div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code"><pre>
$("button").click(function () { 
	$("div").each(function (index, domEle) { 
	  // domEle == this 
	  $(domEle).css("backgroundColor", "yellow");  
	  if ($(this).is("#stop")) { 
		  $("span").text("Stopped at div index #" + index); 
		  return false; 
	  } 
	});
});</pre>
				</div>
			</div>
			<div class="functionitem">size()</div>
			<div class="content">
				<h1>size()</h1>
				<div class="desc"><div>jQuery 对象中元素的个数。</div> <div class="longdesc">这个函数的返回值与 jQuery 对象的'<span title="Core/length">length</span>' 属性一致。</div><hr />
				<div>The number of elements in the jQuery object.</div> <div class="longdesc">This returns the same number as the '<span title="Core/length">length</span>' property of the jQuery object.</div></div>
				<h2>返回值</h2>
				<p class="indent">Number</p>
				<h2>示例</h2>
				<p class="indent">
					计算文档中所有图片数量
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">&lt;img src="test1.jpg"/&gt; &lt;img src="test2.jpg"/&gt;</div>

				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">$("img").size();
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					2
				</div>
			</div>
			<div class="functionitem">length</div>
			<div class="content">
				<h1>length</h1>
				<div class="desc"><div>jQuery 对象中元素的个数。</div> <div class="longdesc">当前匹配的元素个数。 <span title="Core/size">size</span> 将返回相同的值。</div><hr /><div>The number of elements in the jQuery object.</div> <div class="longdesc">The number of elements currently matched. The <span title="Core/size">size</span> function will return the same value.</div></div>
				<h2>返回值</h2>
				<p class="indent">Number</p>
				<h2>示例</h2>
				<p class="indent">
					计算文档中所有图片数量
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">&lt;img src="test1.jpg"/&gt; &lt;img src="test2.jpg"/&gt;</div>

				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">$("img").length;
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					2
				</div>

			</div>
			<div class="functionitem">selector</div>
			<div class="content">
				<h1>selector</h1>
				<div class="desc"><div>jQuery 1.3新增。返回传给jQuery()的原始选择器。</div> <div class="longdesc">换句话说，就是返回你用什么选择器来找到这个元素的。可以与<span title="Core/context">context</span>一起使用，用于精确检测选择器查询情况。这两个属性对插件开发人员很有用。</div><hr /><div>New in jQuery 1.3 A selector representing selector originally passed to jQuery().</div> <div class="longdesc">Should be used in conjunction with <span title="Core/context">context</span> to determine the exact query used. These two properties are mostly useful to plugin developers.</div></div>
				<h2>返回值</h2>
				<p class="indent">Striing</p>
				<h2>示例</h2>
				<p class="indent">
					确定查询的选择器
				</p>

				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
$("ul")<br />
&nbsp;&nbsp;.append("&lt;li&gt;" + $("ul").selector + "&lt;/li&gt;")<br />
&nbsp;&nbsp;.append("&lt;li&gt;" + $("ul li").selector + "&lt;/li&gt;")<br />
&nbsp;&nbsp;.append("&lt;li&gt;" + $("div#foo ul:not([class])").selector + "&lt;/li&gt;");<br />

				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
ul<br />
ul li<br />
div#foo ul:not([class])<br />
				</div>

			</div>
			<div class="functionitem">context</div>
			<div class="content">
				<h1>context</h1>
				<div class="desc"><div>jQuery 1.3新增。返回传给jQuery()的原始的DOM节点内容，即jQuery()的第二个参数。如果没有指定，那么context指向当前的文档(document)。</div> <div class="longdesc">可以与<span title="Core/selector">selector</span>一起使用，用于精确检测选择器查询情况。这两个属性对插件开发人员很有用。</div><hr /><div>New in jQuery 1.3 The DOM node context originally passed to jQuery() (if none was passed then context will be equal to the document).</div> <div class="longdesc">Should be used in conjunction with <span title="Core/selector">selector</span> to determine the exact query used. These two properties are mostly useful to plugin developers.</div></div>
				<h2>返回值</h2>
				<p class="indent">HTMLElement</p>
				<h2>示例</h2>
				<p class="indent">
					检测使用的文档内容
				</p>

				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
$("ul")<br />
&nbsp;&nbsp;.append("&lt;li&gt;" + $("ul").context + "&lt;/li&gt;")<br />
&nbsp;&nbsp;.append("&lt;li&gt;" + $("ul", document.body).context.nodeName + "&lt;/li&gt;");<br />
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
[object HTMLDocument]  //如果是IE浏览器，则返回[object]<br />
BODY<br />
				</div>

			</div>
			<div class="functionitem">get()</div>
			<div class="content">
				<h1>get()</h1>
				<div class="desc"><div>取得所有匹配的 DOM 元素集合。</div> <div class="longdesc"><p>这是取得所有匹配元素的一种向后兼容的方式（不同于jQuery对象，而实际上是元素数组）。</p><p>如果你想要直接操作 DOM 对象而不是 jQuery 对象，这个函数非常有用。</p></div><hr />
				<div>Access all matched DOM elements.</div> <div class="longdesc">This serves as a backwards-compatible way of accessing all matched elements (other than the jQuery object itself, which is, in fact, an array of elements). It is useful if you need to operate on the DOM elements themselves instead of using built-in jQuery functions.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					选择文档中所有图像作为元素数组，并用数组内建的 reverse 方法将数组反向。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;img src="test1.jpg"/&gt; &lt;img src="test2.jpg"/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").get().reverse(); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
				  [ &lt;img src="test2.jpg"/&gt; &lt;img src="test1.jpg"/&gt; ]
				</div>
			</div>
			<div class="functionitem">get(index)</div>
			<div class="content">
				<h1>get(index)</h1>
				<div class="desc"><div>取得其中一个匹配的元素。 num表示取得第几个匹配的元素。</div> <div class="longdesc">这能够让你选择一个实际的DOM 元素并且对他直接操作，而不是通过 jQuery 函数。$(this).get(0)与$(this)[0]等价。</div><hr /><div>Access a single matched DOM element at a specified index in the matched set.</div> <div class="longdesc">This allows you to extract the actual DOM element and operate on it directly without necessarily using jQuery functionality on it. This function called as $(this).get(0) is the equivalent of using square bracket notation on the jQuery object itself like $(this)[0].</div></div>
				<h2>返回值</h2>
				<p class="indent">Element</p>
				<h2>参数</h2>
				<p class="indent"><strong>index </strong>(Number) :取得第 index 个位置上的元素</p>
				<h2>示例</h2>
				<p class="indent">
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;img src="test1.jpg"/&gt; &lt;img src="test2.jpg"/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").get(0); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;img src="test1.jpg"/&gt; ]
				</div>
			</div>
			<div class="functionitem">index(subject)</div>
			<div class="content">
				<h1>index(subject)</h1>
				<div class="desc"><div>搜索与参数表示的对象匹配的元素，并返回相应元素的索引值。</div> <div class="longdesc">如果找到了匹配的元素，从0开始返回；如果没有找到匹配的元素，返回-1。</div><hr /><div>Searches every matched element for the object and returns the index of the element, if found, starting with zero.</div> <div class="longdesc">Returns -1 if the object wasn't found.</div></div>
				<h2>返回值</h2>
				<p class="indent">Number</p>
				<h2>参数</h2>
				<p class="indent"><strong>subject </strong>(Element) : 要搜索的对象</p>
				<h2>示例</h2>
				<p class="indent">
					返回ID值为foobar的元素的索引值。 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div id="foobar"&gt;&lt;div&gt;&lt;/div&gt;&lt;div id="foo"&gt;&lt;/div&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").index($('#foobar')[0])  // 0<br/>
					$("div").index($('#foo')[0])  // 2<br/>
					$("div").index($('#foo'))  // -1<br/>
				</div>
			</div>
		</div>
		<div class="categoryitem">数据缓存</div>
		<div class="category">
			<div class="functionitem">data(name)</div>
			<div class="content">
				<h1>data(name)</h1>
				<div class="desc"><div>返回元素上储存的相应名字的数据，可以用data(name, value)来设定。</div><div class="longdesc"><p>如果jQuery集合指向多个元素，那将只返回第一个元素的对应数据。</p><p>这个函数可以用于在一个元素上存取数据而避免了循环引用的风险。jQuery.data是1.2.3版的新功能。你可以在很多地方使用这个函数，另外jQuery UI里经常使用这个函数。</p></div>
				<hr /><div>Returns value at named data store for the element, as set by data(name, value).</div> <div class="longdesc"><p>If the JQuery collection references multiple elements, the value returned refers to the first element.</p><p>This function is used to get stored data on an element without the risk of a circular reference. It uses jQuery.data and is new to version 1.2.3. It can be used for many reasons and jQuery UI uses it heavily. </p></div></div>
				<h2>返回值</h2>
				<p class="indent">Any</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) :存储的数据名 </p>
				<h2>示例</h2>
				<p class="indent">
					在一个div上存取数据
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;/div&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").data("blah");  // undefined<br/>
					$("div").data("blah", "hello");  // blah设置为hello<br/>
					$("div").data("blah");  // hello<br/>
					$("div").data("blah", 86);  // 设置为86<br/>
					$("div").data("blah");  //  86<br/>
					$("div").removeData("blah");  //移除blah<br />
					$("div").data("blah");  // undefined
				</div>
				<hr />
				<p class="indent">
					在一个div上存取名/值对数据
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;/div&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").data("test", { first: 16, last: "pizza!" });<br />
					$("div").data("test").first  //16;<br />
					$("div").data("test").last  //pizza!;
				</div>
			</div>
			<div class="functionitem">data(name,value)</div>
			<div class="content">
				<h1>data(name,value)</h1>
				<div class="desc"><div>在元素上存放数据，同时也返回value。</div><div class="longdesc"><p>如果jQuery集合指向多个元素，那将在所有元素上设置对应数据。</p><p>这个函数不用建立一个新的expando，就能在一个元素上存放任何格式的数据，而不仅仅是字符串。</p></div>
				<hr /><div>Stores the value in the named spot and also returns the value.</div> <div class="longdesc"><p>If the JQuery collection references multiple elements, the data element is set on all of them.</p><p>This function can be useful for attaching data to elements without having to create a new expando. It also isn't limited to a string. The value can be any format.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">Any</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) :存储的数据名 </p>
				<p class="indent"><strong>value </strong>(Any) :将要存储的任意数据 </p>
				<h2>示例</h2>
				<p class="indent">
					参考data(name)的示例
				</p>
			</div>
			<div class="functionitem">removeData(name)</div>
			<div class="content">
				<h1>removeData(name)</h1>
				<div class="desc"><div>在元素上移除存放的数据</div><div class="longdesc">与$(...).data(name, value)函数作用相反</div>
				<hr /><div>Removes named data store from an element.</div> <div class="longdesc">This is the complement function to $(...).data(name, value).</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) :存储的数据名 </p>
				<h2>示例</h2>
				<p class="indent">
					参考data(name)的示例
				</p>
			</div>
			<div class="functionitem">queue([name] )</div>
			<div class="content">
				<h1>queue([name])</h1>
				<div class="desc">返回指向第一个匹配元素的队列(将是一个函数数组)</div><hr />
				<div class="desc">Returns a reference to the first element's queue (which is an array of functions).</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Function&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) :队列名，默认为fx </p>
				<h2>示例</h2>
				<p class="indent">
					显示队列长度
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code"><pre>
  &lt;style&gt;
  div { margin:3px; width:40px; height:40px;
        position:absolute; left:0px; top:30px; 
        background:green; display:none; }
  div.newcolor { background:blue; }
  span { color:red; }
  &lt;/style&gt;
  &lt;button id="show">Show Length of Queue&lt;/button&gt;
  &lt;span&gt;&lt;/span&gt;
  &lt;div&gt;&lt;/div&gt;</pre>		
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code"><pre>
$("#show").click(function () {
      var n = $("div").queue("fx");
      $("span").text("Queue length is: " + n.length);
});
function runIt() {
      $("div").show("slow");
      $("div").animate({left:'+=200'},2000);
      $("div").slideToggle(1000);
      $("div").slideToggle("fast");
      $("div").animate({left:'-=200'},1500);
      $("div").hide("slow");
      $("div").show(1200);
      $("div").slideUp("normal", runIt);
}
runIt();</pre>
				</div>
			</div>
			<div class="functionitem">queue([name],callback)</div>
			<div class="content">
				<h1>queue(callback)</h1>
				<div class="desc">在匹配的元素的队列最后添加一个函数</div><hr />
				<div class="desc">Adds a new function, to be executed, onto the end of the queue of all matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) :队列名，默认为fx </p>
				<p class="indent"><strong>callback </strong>(Function) : 要添加进队列的函数</p>
				<h2>示例</h2>
				<p class="indent">
					插入一个自定义函数
					如果函数执行后要继续队列，则执行 jQuery(this).dequeue();
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code"><pre>
  &lt;style&gt;
  div { margin:3px; width:40px; height:40px;
        position:absolute; left:0px; top:30px; 
        background:green; display:none; }
  div.newcolor { background:blue; }
  &lt;/style&gt;
  Click here...
  &lt;div&gt;&lt;/div&gt;</pre>		
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code"><pre>
$(document.body).click(function () {
      $("div").show("slow");
      $("div").animate({left:'+=200'},2000);
      $("div").queue(function () {
          $(this).addClass("newcolor");
          $(this).dequeue();
      });
      $("div").animate({left:'-=200'},500);
      $("div").queue(function () {
          $(this).removeClass("newcolor");
          $(this).dequeue();
      });
      $("div").slideUp();
});
</pre>
				</div>
			</div>
			<div class="functionitem">queue([name],queue)</div>
			<div class="content">
				<h1>queue(queue)</h1>
				<div class="desc">将匹配元素的队列用新的一个队列来代替(函数数组).</div><hr /><hr />
				<div class="desc">Replaces the queue of all matched element with this new queue (the array of functions).</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) :队列名，默认为fx </p>
				<p class="indent"><strong>queue </strong>(Array&lt;Function&gt;) : 用于替换的队列。所有函数都有同一个参数，这个值与queue(callback)相同</p>
				<h2>示例</h2>
				<p class="indent">
					通过设定队列数组来删除动画队列
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code"><pre>
  &lt;style&gt;
  div { margin:3px; width:40px; height:40px;
        position:absolute; left:0px; top:30px; 
        background:green; display:none; }
  div.newcolor { background:blue; }
  &lt;/style&gt;

  &lt;button id="start"&gt;Start&lt;/button&gt;
  &lt;button id="stop"&gt;Stop&lt;/button&gt;
  &lt;div&gt;&lt;/div&gt;</pre>
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code"><pre>
  $("#start").click(function () {
      $("div").show("slow");
      $("div").animate({left:'+=200'},5000);
      $("div").queue(function () {
          $(this).addClass("newcolor");
          $(this).dequeue();
      });
      $("div").animate({left:'-=200'},1500);
      $("div").queue(function () {
          $(this).removeClass("newcolor");
          $(this).dequeue();
      });
      $("div").slideUp();
  });
  $("#stop").click(function () {
      $("div").queue("fx", []);
      $("div").stop();
  });</pre>
				</div>
			</div>
			<div class="functionitem">dequeue([name])</div>
			<div class="content">
				<h1>dequeue([name])</h1>
				<div class="desc">从队列最前端移除一个队列函数，并执行他。</div><hr />
				<div class="desc">Removes a queued function from the front of the queue and executes it.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) :队列名，默认为fx </p>
				<h2>示例</h2>
				<p class="indent">
					用dequeue来结束自定义队列函数，并让队列继续进行下去。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code"><pre>
  &lt;style&gt;
  div { margin:3px; width:50px; position:absolute;
        height:50px; left:10px; top:30px; 
        background-color:yellow; }
  div.red { background-color:red; }
  &lt;/style&gt;

  &lt;button&gt;Start&lt;/button&gt;
  &lt;div&gt;&lt;/div&gt;</pre>
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code"><pre>
  $("button").click(function () {
      $("div").animate({left:'+=200px'}, 2000);
      $("div").animate({top:'0px'}, 600);
      $("div").queue(function () {
          $(this).toggleClass("red");
          $(this).dequeue();
      });
      $("div").animate({left:'10px', top:'30px'}, 700);
  });</pre>
				</div>
			</div>
		</div>
		<div class="categoryitem">插件机制</div>
		<div class="category">
			<div class="functionitem">jQuery.fn.extend(object)</div>
			<div class="content">
				<h1>jQuery.fn.extend(object)</h1>
				<div class="desc"><div>扩展 jQuery 元素集来提供新的方法（通常用来制作插件）。</div> <div class="longdesc">查看这里<a href="http://docs.jquery.com/Plugins/Authoring" title="Plugins/Authoring">Plugins/Authoring</a>可以获取更多信息。</div>
				<hr /><div>Extends the jQuery element set to provide new methods (used to make a typical jQuery plugin).</div> <div class="longdesc">Can be used to add functions into the to add <a href="http://docs.jquery.com/Plugins/Authoring" title="Plugins/Authoring">plugin methods (plugins)</a>.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>object </strong>(Object) :用来扩充 jQuery 对象。 </p>
				<h2>示例</h2>
				<p class="indent">
					增加两个插件方法。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					jQuery.fn.extend({<br/>
&nbsp; check: function() {<br/>
&nbsp;&nbsp;&nbsp; return this.each(function() { this.checked = true; });<br/>
&nbsp; },<br/>
&nbsp; uncheck: function() {<br/>
&nbsp;&nbsp;&nbsp; return this.each(function() { this.checked = false; });<br/>
&nbsp; }<br/>
});
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					$("input[type=checkbox]").check();<br/>
$("input[type=radio]").uncheck(); 
				</div>
			</div>
			<div class="functionitem">jQuery.extend(object)</div>
			<div class="content">
				<h1>jQuery.extend(object)</h1>
				<div class="desc"><div>扩展jQuery对象本身。</div> <div class="longdesc">用来在jQuery命名空间上增加新函数。 查看 <span title="Core/jQuery.fn.extend">'jQuery.fn.extend'</span> 获取更多添加<a href="http://docs.jquery.com/Plugins/Authoring" title="Plugins/Authoring">插件</a>的信息。</div><hr />
				<div>Extends the jQuery object itself.</div> <div class="longdesc">Can be used to add functions into the jQuery namespace. See <span title="Core/jQuery.fn.extend">'jQuery.fn.extend'</span> for more information on using this method to add <a href="http://docs.jquery.com/Plugins/Authoring" title="Plugins/Authoring">Plugins</a>.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>object </strong>(Object) : 用以扩展 jQuery 对象</p>
				<h2>示例</h2>
				<p class="indent">
					在jQuery命名空间上增加两个函数。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					jQuery.extend({<br/>
&nbsp; min: function(a, b) { return a &lt; b ? a : b; },<br/>
&nbsp; max: function(a, b) { return a &gt; b ? a : b; }<br/>
});
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					jQuery.min(2,3); // =&gt; 2<br/>
jQuery.max(4,5); // =&gt; 5 
				</div>
			</div>
		</div>
		<div class="categoryitem">多库共存</div>
		<div class="category">
			<div class="functionitem">jQuery.noConflict()</div>
			<div class="content">
				<h1>jQuery.noConflict()</h1>
				<div class="desc"><div>运行这个函数将变量$的控制权让渡给第一个实现它的那个库。</div> <div class="longdesc"><p>这有助于确保jQuery不会与其他库的$对象发生冲突。</p><p>在运行这个函数后，就只能使用jQuery变量访问jQuery对象。例如，在要用到$("div p")的地方，就必须换成jQuery("div p")。</p><p><strong>注意:</strong>这个函数必须在你导入jQuery文件之后，并且在导入另一个导致冲突的库<strong>之前</strong>使用。当然也应当在其他冲突的库被使用之前，除非jQuery是最后一个导入的。</p></div><hr /><div>Run this function to give control of the $ variable back to whichever library first implemented it.</div><div class="longdesc"><p>This helps to make sure that jQuery doesn't conflict with the $ object of other libraries.</p><p>By using this function, you will only be able to access jQuery using the 'jQuery' variable. For example, where you used to do $("div p"), you now must do jQuery("div p").</p><p><strong>NOTE:</strong> This function must be called after including the jQuery javascript file, but <strong>before</strong> including any other conflicting library, and also before actually that other conflicting library gets used, in case jQuery is included last.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					将$引用的对象映射回原始的对象。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					jQuery.noConflict();<br/>
// 使用 jQuery<br/>
jQuery("div p").hide();<br/>
// 使用其他库的 $()<br/>
$("content").style.display = 'none'; 
				</div>
				<hr />
				<p class="indent">
					恢复使用别名$，然后创建并执行一个函数，在这个函数的作用域中仍然将$作为jQuery的别名来使用。在这个函数中，原来的$对象是无效的。这个函数对于大多数不依赖于其他库的插件都十分有效。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					jQuery.noConflict();<br/>
(function($) { <br/>
&nbsp; $(function() {<br/>
&nbsp;&nbsp;&nbsp; // 使用 $ 作为 jQuery 别名的代码<br/>
&nbsp; });<br/>
})(jQuery);<br/>
// 其他用 $ 作为别名的库的代码  
				</div>
				<hr />
				<p class="indent">
					创建一个新的别名用以在接下来的库中使用jQuery对象。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var j = jQuery.noConflict();<br/>
// 基于 jQuery 的代码<br/>
j("div p").hide();<br/>
// 基于其他库的 $() 代码<br/>
$("content").style.display = 'none'; 
				</div>
			</div>
			<div class="functionitem">jQuery.noConflict(extreme)</div>
			<div class="content">
				<h1>jQuery.noConflict(extreme)</h1>
				<div class="desc">
				<div>将$和jQuery的控制权都交还给原来的库。<strong>用之前请考虑清楚!</strong></div> <div class="longdesc">这是相对于简单的 <strong>noConflict</strong> 方法更极端的版本，因为这将完全重新定义jQuery。这通常用于一种极端的情况，比如你想要将jQuery嵌入一个高度冲突的环境。<strong>注意:</strong>调用此方法后极有可能导致插件失效。</div><hr /><div>Revert control of both the $ and jQuery variables to their original owners. <strong>Use with discretion.</strong></div> <div class="longdesc">This is a more-extreme version of the simple <strong>noConflict</strong> method, as this one will completely undo what jQuery has introduced. This is to be used in an extreme case where you'd like to embed jQuery into a high-conflict environment. <strong>NOTE:</strong> It's very likely that plugins won't work after this particular method has been called.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>extreme </strong>(Boolean) : 传入 true 来允许彻底将jQuery变量还原</p>
				<h2>示例</h2>
				<p class="indent">
					完全将 jQuery 移到一个新的命名空间。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var dom = {};<br/>
dom.query = jQuery.noConflict(true); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					// 新 jQuery 的代码<br/>
dom.query("div p").hide();<br/>
// 另一个库 $() 的代码<br/>
$("content").style.display = 'none';<br/>
// 另一个版本 jQuery 的代码<br/>
jQuery("div &gt; p").hide(); 
				</div>
			</div>
		</div>
	</div>
	<!-- Selectors -->
	<div class="menuitem">选择器</div>
	<div class="functionmenu">
		<div class="categoryitem">基本</div>
		<div class="category">
			<div class="functionitem">#id</div>
			<div class="content">
				<h1>#id</h1>
				<div class="desc">根据给定的ID匹配一个元素。</div><div class="longdesc">如果选择器中包含特殊字符，可以用两个斜杠转义。参见示例。</div><hr />
				<div class="desc">Matches a single element with the given id attribute.</div>
				<h2>返回值</h2>
				<p class="indent">Element</p>
				<h2>参数</h2>
				<p class="indent"><strong>id </strong>(String) : 用于搜索的，通过元素的 id 属性中给定的值</p>
				<h2>示例</h2>
				<p class="indent">
					查找 ID 为"myDiv"的元素。 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div id="notMe"&gt;&lt;p&gt;id="notMe"&lt;/p&gt;&lt;/div&gt;<br/>
&lt;div id="myDiv"&gt;id="myDiv"&lt;/div&gt; <br/>
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#myDiv"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div id="myDiv"&gt;id="myDiv"&lt;/div&gt; ] 
				</div>
				<p class="indent">
					查找含有特殊字符的元素 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
&lt;span id="foo:bar"&gt;&lt;/span&gt;<br />
&lt;span id="foo[bar]"&gt;&lt;/span&gt;<br />
&lt;span id="foo.bar"&gt;&lt;/span&gt;<br />
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					#foo\\:bar<br />
					#foo\\[bar\\]<br />
					#foo\\.bar<br />
				</div>
			</div>
			<div class="functionitem">element</div>
			<div class="content">
				<h1>element</h1>
				<div class="desc">根据给定的元素名匹配所有元素</div><hr />
				<div class="desc">Matches all elements with the given name.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>element </strong>(String) : 一个用于搜索的元素。指向 DOM 节点的标签名。</p>
				<h2>示例</h2>
				<p class="indent">
					查找一个 DIV 元素。 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;DIV1&lt;/div&gt;<br/>
&lt;div&gt;DIV2&lt;/div&gt;<br/>
&lt;span&gt;SPAN&lt;/span&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div&gt;DIV1&lt;/div&gt;, &lt;div&gt;DIV2&lt;/div&gt; ] 
				</div>
			</div>
			<div class="functionitem">.class</div>
			<div class="content">
				<h1>.class</h1>
				<div class="desc">根据给定的类匹配元素。</div><hr />
				<div class="desc">Matches all elements with the given class.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>class </strong>(String) : 一个用以搜索的类。一个元素可以有多个类，只要有一个符合就能被匹配到。</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有类是 "myClass" 的元素. 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div class="notMe"&gt;div class="notMe"&lt;/div&gt;<br/>
&lt;div class="myClass"&gt;div class="myClass"&lt;/div&gt;<br/>
&lt;span class="myClass"&gt;span class="myClass"&lt;/span&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(".myClass"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div class="myClass"&gt;div class="myClass"&lt;/div&gt;, &lt;span class="myClass"&gt;span class="myClass"&lt;/span&gt; ] 
				</div>
			</div>
			<div class="functionitem">*</div>
			<div class="content">
				<h1>*</h1>
				<div class="desc"><div>匹配所有元素</div> <div class="longdesc">多用于结合上下文来搜索。</div><hr /><div>Matches all elements.</div> <div class="longdesc">Most useful when combined with a context to search in.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					找到每一个元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;DIV&lt;/div&gt;<br/>
&lt;span&gt;SPAN&lt;/span&gt;<br/>
&lt;p&gt;P&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("*") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div&gt;DIV&lt;/div&gt;, &lt;span&gt;SPAN&lt;/span&gt;, &lt;p&gt;P&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">selector1,selector2,selectorN</div>
			<div class="content">
				<h1>selector1,selector2,selectorN</h1>
				<div class="desc"><div>将每一个选择器匹配到的元素合并后一起返回。</div> <div class="longdesc">你可以指定任意多个选择器，并将匹配到的元素合并到一个结果内。</div><hr /><div>Matches the combined results of all the specified selectors.</div> <div class="longdesc">You can specify any number of selectors to combine into a single result.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>selector1 </strong>(Selector) : 一个有效的选择器</p>
				<p class="indent"><strong>selector2 </strong>(Selector) : 另一个有效的选择器</p>
				<p class="indent"><strong>selectorN </strong>(Selector) : (可选) 任意多个有效选择器</p>
				<h2>示例</h2>
				<p class="indent">
					找到匹配任意一个类的元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;div&lt;/div&gt;<br/>
&lt;p class="myClass"&gt;p class="myClass"&lt;/p&gt;<br/>
&lt;span&gt;span&lt;/span&gt;<br/>
&lt;p class="notMyClass"&gt;p class="notMyClass"&lt;/p&gt; <br/>
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div,span,p.myClass") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div&gt;div&lt;/div&gt;, &lt;p class="myClass"&gt;p class="myClass"&lt;/p&gt;, &lt;span&gt;span&lt;/span&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">层级</div>
		<div class="category">
			<div class="functionitem">ancestor descendant</div>
			<div class="content">
				<h1>ancestor descendant</h1>
				 <div class="desc">在给定的祖先元素下匹配所有的后代元素</div><hr />
				 <div class="desc">Matches all descendant elements specified by descendant of elements specified by ancestor.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>ancestor </strong>(Selector) : 任何有效选择器</p>
				<p class="indent"><strong>descendant </strong>(Selector) : 用以匹配元素的选择器，并且它是第一个选择器的后代元素</p>
				<h2>示例</h2>
				<p class="indent">
					找到表单中所有的 input 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;label&gt;Name:&lt;/label&gt;<br/>
&nbsp; &lt;input name="name" /&gt;<br/>
&nbsp; &lt;fieldset&gt;<br/>
&nbsp; &nbsp;&nbsp;&nbsp; &lt;label&gt;Newsletter:&lt;/label&gt;<br/>
&nbsp; &nbsp;&nbsp;&nbsp; &lt;input name="newsletter" /&gt;<br/>
&nbsp;&lt;/fieldset&gt;<br/>
&lt;/form&gt;<br/>
&lt;input name="none" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form input") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="name" /&gt;, &lt;input name="newsletter" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">parent &gt; child</div>
			<div class="content">
				<h1>parent &gt; child</h1>
				<div class="desc">在给定的父元素下匹配所有的子元素</div><hr />
				<div class="desc">Matches all child elements specified by child of elements specified by parent.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>parent </strong>(Selector) : 任何有效选择器</p>
				<p class="indent"><strong>child </strong>(Selector) : 用以匹配元素的选择器，并且它是第一个选择器的子元素</p>
				<h2>示例</h2>
				<p class="indent">
					匹配表单中所有的子级input元素。 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;label&gt;Name:&lt;/label&gt;<br/>
&nbsp; &lt;input name="name" /&gt;<br/>
&nbsp; &lt;fieldset&gt;<br/>
&nbsp; &nbsp;&nbsp;&nbsp; &lt;label&gt;Newsletter:&lt;/label&gt;<br/>
&nbsp; &nbsp;&nbsp;&nbsp; &lt;input name="newsletter" /&gt;<br/>
&nbsp;&lt;/fieldset&gt;<br/>
&lt;/form&gt;<br/>
&lt;input name="none" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form &gt; input") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="name" /&gt; ]
				</div>
			</div>
			<div class="functionitem">prev + next</div>
			<div class="content">
				<h1>prev + next</h1>
				<div class="desc">匹配所有紧接在 prev 元素后的 next 元素</div><hr />
				<div class="desc">Matches all next elements specified by next that are next to elements specified by prev.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>prev </strong>(Selector) : 任何有效选择器</p>
				<p class="indent"><strong>next </strong>(Selector) :一个有效选择器并且紧接着第一个选择器</p>
				<h2>示例</h2>
				<p class="indent">
					匹配所有跟在 label 后面的 input 元素 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;label&gt;Name:&lt;/label&gt;<br/>
&nbsp; &lt;input name="name" /&gt;<br/>
&nbsp; &lt;fieldset&gt;<br/>
&nbsp; &nbsp;&nbsp;&nbsp; &lt;label&gt;Newsletter:&lt;/label&gt;<br/>
&nbsp; &nbsp;&nbsp;&nbsp; &lt;input name="newsletter" /&gt;<br/>
&nbsp;&lt;/fieldset&gt;<br/>
&lt;/form&gt;<br/>
&lt;input name="none" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("label + input") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="name" /&gt;, &lt;input name="newsletter" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">prev ~ siblings</div>
			<div class="content">
				<h1>prev ~ siblings</h1>
				<div class="desc">匹配 prev 元素之后的所有 siblings 元素</div><hr />
				<div class="desc">Matches all sibling elements after the "prev" element that match the filtering "siblings" selector.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>prev </strong>(Selector) : 任何有效选择器</p>
				<p class="indent"><strong>siblings </strong>(Selector) : 一个选择器，并且它作为第一个选择器的同辈</p>
				<h2>示例</h2>
				<p class="indent">
					找到所有与表单同辈的 input 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;label&gt;Name:&lt;/label&gt;<br/>
&nbsp; &lt;input name="name" /&gt;<br/>
&nbsp; &lt;fieldset&gt;<br/>
&nbsp; &nbsp;&nbsp;&nbsp; &lt;label&gt;Newsletter:&lt;/label&gt;<br/>
&nbsp; &nbsp;&nbsp;&nbsp; &lt;input name="newsletter" /&gt;<br/>
&nbsp;&lt;/fieldset&gt;<br/>
&lt;/form&gt;<br/>
&lt;input name="none" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form ~ input") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="none" /&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">简单</div>
		<div class="category">
			<div class="functionitem">:first</div>
			<div class="content">
				<h1>:first</h1>
				<div class="desc">匹配找到的第一个元素</div><hr />
				<div class="desc">Matches the first selected element.</div>
				<h2>返回值</h2>
				<p class="indent">Element</p>
				<h2>示例</h2>
				<p class="indent">
					查找表格的第一行
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:first") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
			<div class="functionitem">:last</div>
			<div class="content">
				<h1>:last</h1>
				<div class="desc">匹配找到的最后一个元素</div><hr />  <div class="desc">Matches the last selected element.</div>
				<h2>返回值</h2>
				<p class="indent">Element</p>
				<h2>示例</h2>
				<p class="indent">
					查找表格的最后一行
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:last") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
			<div class="functionitem">:not(selector)</div>
			<div class="content">
				<h1>:not(selector)</h1>
				<div class="desc">去除所有与给定选择器匹配的元素</div><div class="longdesc">在jQuery 1.3中，已经支持复杂选择器了（例如:not(div a) 和 :not(div,a)）</div><hr />
				<div class="desc">Removes all elements matching the given selector.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>selector </strong>(Selector) : 用于筛选的选择器</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有未选中的 input 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input name="apple" /&gt;<br/>
&lt;input name="flower" checked="checked" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input:not(:checked)") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[&nbsp;&lt;input name="apple" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">:even</div>
			<div class="content">
				<h1>:even</h1>
				<div class="desc">匹配所有索引值为偶数的元素，从 0 开始计数</div><hr />
				<div class="desc">Matches even elements, zero-indexed.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找表格的1、3、5...行（即索引值0、2、4...）
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:even") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;, &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
			<div class="functionitem">:odd</div>
			<div class="content">
				<h1>:odd</h1>
				<div class="desc">匹配所有索引值为奇数的元素，从 0 开始计数</div><hr />  <div class="desc">Matches odd elements, zero-indexed.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找表格的2、4、6行（即索引值1、3、5...）
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:odd") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
			<div class="functionitem">:eq(index)</div>
			<div class="content">
				<h1>:eq(index)</h1>
				<div class="desc">匹配一个给定索引值的元素</div><hr />
				<div class="desc">Matches a single element by its index.</div>
				<h2>返回值</h2>
				<p class="indent">Element</p>
				<h2>参数</h2>
				<p class="indent"><strong>index </strong>(Number) : 从 0 开始计数</p>
				<h2>示例</h2>
				<p class="indent">
					查找第二行
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:eq(1)") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
			<div class="functionitem">:gt(index)</div>
			<div class="content">
				<h1>:gt(index)</h1>
				<div class="desc">匹配所有大于给定索引值的元素</div> <hr /> <div class="desc">Matches all elements with an index above the given one.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>index </strong>(Number) : 从 0 开始计数</p>
				<h2>示例</h2>
				<p class="indent">
					查找第二第三行，即索引值是1和2，也就是比0大
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:gt(0)") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;, &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
			<div class="functionitem">:lt(index)</div>
			<div class="content">
				<h1>:lt(index)</h1>
				<div class="desc">匹配所有小于给定索引值的元素</div><hr />
				<div class="desc">Matches all elements with an index below the given one.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>index </strong>(Number) : 从 0 开始计数</p>
				<h2>示例</h2>
				<p class="indent">
					查找第一第二行，即索引值是0和1，也就是比2小
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:lt(2)") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr&gt;&lt;td&gt;Header 1&lt;/td&gt;&lt;/tr&gt;, &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
			<div class="functionitem">:header</div>
			<div class="content">
				<h1>:header</h1>
				<div class="desc">匹配如 h1, h2, h3之类的标题元素</div><hr />
				<div class="desc">Matches all elements that are headers, like h1, h2, h3 and so on.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					给页面内所有标题加上背景色
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;h1&gt;Header 1&lt;/h1&gt;<br/>
&lt;p&gt;Contents 1&lt;/p&gt;<br/>
&lt;h2&gt;Header 2&lt;/h2&gt;<br/>
&lt;p&gt;Contents 2&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":header").css("background", "#EEE"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;h1 style="background:#EEE;"&gt;Header 1&lt;/h1&gt;, &lt;h2 style="background:#EEE;"&gt;Header 2&lt;/h2&gt; ] 
				</div>
			</div>
			<div class="functionitem">:animated</div>
			<div class="content">
				<h1>:animated</h1><div class="desc">匹配所有正在执行动画效果的元素</div><hr />
				<div class="desc">Matches all elements that are currently being animated.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					只有对不在执行动画效果的元素执行一个动画特效
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;button id="run"&gt;Run&lt;/button&gt;&lt;div&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#run").click(function(){<br/>
&nbsp; $("div:not(:animated)").animate({ left: "+=20" }, 1000);<br/>
});
				</div>
			</div>
		</div>
		<div class="categoryitem">内容</div>
		<div class="category">
			<div class="functionitem">:contains(text)</div>
			<div class="content">
				<h1>:contains(text)</h1>
				<div class="desc">匹配包含给定文本的元素</div>  <hr />   <div class="desc">Matches elements which contain the given text.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>text </strong>(String) : 一个用以查找的字符串</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有包含 "John" 的 div 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;John Resig&lt;/div&gt;<br/>
&lt;div&gt;George Martin&lt;/div&gt;<br/>
&lt;div&gt;Malcom John Sinclair&lt;/div&gt;<br/>
&lt;div&gt;J. Ohn 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div:contains('John')") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div&gt;John Resig&lt;/div&gt;, &lt;div&gt;Malcom John Sinclair&lt;/div&gt; ] 
				</div>
			</div>
			<div class="functionitem">:empty</div>
			<div class="content">
				<h1>:empty</h1>
				<div class="desc">匹配所有不包含子元素或者文本的空元素</div><hr />
				<div class="desc">Matches all elements that are empty, be it elements or text.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有不包含子元素或者文本的空元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;td&gt;&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;td&gt;&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("td:empty") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;td&gt;&lt;/td&gt;, &lt;td&gt;&lt;/td&gt; ] 
				</div>
			</div>
			<div class="functionitem">:has(selector)</div>
			<div class="content">
				<h1>:has(selector)</h1>
				<div class="desc">匹配含有选择器所匹配的元素的元素</div><hr /><div class="desc">Matches elements which contain at least one element that matches the specified selector.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>selector </strong>(Selector) : 一个用于筛选的选择器</p>
				<h2>示例</h2>
				<p class="indent">
					给所有包含 p 元素的 div 元素添加一个 text 类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;/div&gt;<br/>
&lt;div&gt;Hello again!&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div:has(p)").addClass("test"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div class="test"&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;/div&gt; ] 
				</div>
			</div>
			<div class="functionitem">:parent</div>
			<div class="content">
				<h1>:parent</h1>
				<div class="desc">匹配含有子元素或者文本的元素</div><hr />
				<div class="desc">Matches all elements that are parents - they have child elements, including text.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有含有子元素或者文本的 td 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;td&gt;&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;td&gt;&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("td:parent") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;td&gt;Value 1&lt;/td&gt;, &lt;td&gt;Value 1&lt;/td&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">可见性</div>
		<div class="category">
			<div class="functionitem">:hidden</div>
			<div class="content">
				<h1>:hidden</h1>
				<div class="desc">匹配所有的不可见元素，input 元素的 type 属性为 "hidden" 的话也会被匹配到</div><hr />
				<div class="desc">Matches all elements that are hidden, or input elements of type "hidden".</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有不可见的 tr 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr style="display:none"&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:hidden") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr style="display:none"&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
			<div class="functionitem">:visible</div>
			<div class="content">
				<h1>:visible</h1>
				<div class="desc">匹配所有的可见元素</div> <hr /> <div class="desc">Matches all elements that are visible.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有可见的 tr 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr style="display:none"&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:visible") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">属性</div>
		<div class="category">
			<div class="functionitem">[attribute]</div>
			<div class="content">
				<h1>[attribute]</h1>
				<div class="desc">匹配包含给定属性的元素。注意，在jQuery 1.3中，前导的@符号已经被废除！如果想要兼容最新版本，只需要简单去掉@符号即可。</div><hr /> <div class="desc">Matches elements that have the specified attribute.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>attribute </strong>(String) : 属性名</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有含有 id 属性的 div 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;<br/>
&nbsp; &lt;p&gt;Hello!&lt;/p&gt;<br/>
&lt;/div&gt;<br/>
&lt;div id="test2"&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div[id]") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div id="test2"&gt;&lt;/div&gt; ] 
				</div>
			</div>
			<div class="functionitem">[attribute=value]</div>
			<div class="content">
				<h1>[attribute=value]</h1>
				<div class="desc">匹配给定的属性是某个特定值的元素</div> <hr /> <div class="desc">Matches elements that have the specified attribute with a certain value.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>attribute </strong>(String) : 属性名</p>
				<p class="indent"><strong>value </strong>(String) : 属性值。引号在大多数情况下是可选的。但在遇到诸如属性值包含"]"时，用以避免冲突。</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有 name 属性是 newsletter 的 input 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input type="checkbox" name="newsletter" value="Hot Fuzz" /&gt;<br/>
&lt;input type="checkbox" name="newsletter" value="Cold Fusion" /&gt;<br/>
&lt;input type="checkbox" name="accept" value="Evil Plans" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[name='newsletter']").attr("checked", true); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="checkbox" name="newsletter" value="Hot Fuzz" checked="true" /&gt;, &lt;input type="checkbox" name="newsletter" value="Cold Fusion" checked="true" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">[attribute!=value]</div>
			<div class="content">
				<h1>[attribute!=value]</h1>
				<div class="desc">匹配所有含有指定的属性，但属性不等于特定值的元素。<div class="longdesc">在jQuery 1.3之前是这样的："匹配那些没有指定的属性的元素，或者指定的属性不等于特定值的元素。"，这等价于:not([attr=value])</div></div> <hr /> <div class="desc">Matches elements that have the specified attribute but not with a certain value.<div class="longdesc">
					Prior to jQuery 1.3 the selector worked differently: "Matches elements that either don't have the specified attribute or do have the specified attribute but not with a certain value." That functionality is equivalent to :not([attr=value]).
				</div></div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>attribute </strong>(String) : 属性名</p>
				<p class="indent"><strong>value </strong>(String) : 属性值。引号在大多数情况下是可选的。但在遇到诸如属性值包含"]"时，用以避免冲突。</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有 name 属性不是 newsletter 的 input 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input type="checkbox" name="newsletter" value="Hot Fuzz" /&gt;<br/>
&lt;input type="checkbox" name="newsletter" value="Cold Fusion" /&gt;<br/>
&lt;input type="checkbox" name="accept" value="Evil Plans" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[name!='newsletter']").attr("checked", true); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="checkbox" name="accept" value="Evil Plans" checked="true" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">[attribute^=value]</div>
			<div class="content">
				<h1>[attribute^=value]</h1>
				<div class="desc">匹配给定的属性是以某些值开始的元素</div><hr />
				<div class="desc">Matches elements that have the specified attribute and it starts with a certain value.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>attribute </strong>(String) : 属性名</p>
				<p class="indent"><strong>value </strong>(	String) : 属性值。引号在大多数情况下是可选的。但在遇到诸如属性值包含"]"时，用以避免冲突。</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有 name 以 'news' 开始的 input 元素 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input name="newsletter" /&gt;<br/>
&lt;input name="milkman" /&gt;<br/>
&lt;input name="newsboy" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[name^='news']") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="newsletter" /&gt;, &lt;input name="newsboy" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">[attribute$=value]</div>
			<div class="content">
				<h1>[attribute$=value]</h1>
				<div class="desc">匹配给定的属性是以某些值结尾的元素</div><hr />
				<div class="desc">Matches elements that have the specified attribute and it ends with a certain value.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>attribute </strong>(String) : 属性名</p>
				<p class="indent"><strong>value </strong>(String) : 属性值。引号在大多数情况下是可选的。但在遇到诸如属性值包含"]"时，用以避免冲突。</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有 name 以 'letter' 结尾的 input 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input name="newsletter" /&gt;<br/>
&lt;input name="milkman" /&gt;<br/>
&lt;input name="jobletter" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[name$='letter']") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="newsletter" /&gt;, &lt;input name="jobletter" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">[attribute*=value]</div>
			<div class="content">
				<h1>[attribute*=value]</h1>
				<div class="desc">匹配给定的属性是以包含某些值的元素</div><hr />
				<div class="desc">Matches elements that have the specified attribute and it contains a certain value.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>attribute </strong>(String) : 属性名</p>
				<p class="indent"><strong>value </strong>(String) : 属性值。引号在大多数情况下是可选的。但在遇到诸如属性值包含"]"时，用以避免冲突。</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有 name 包含 'man' 的 input 元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input name="man-news" /&gt;<br/>
&lt;input name="milkman" /&gt;<br/>
&lt;input name="letterman2" /&gt;<br/>
&lt;input name="newmilk" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[name*='man']") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="man-news" /&gt;, &lt;input name="milkman" /&gt;, &lt;input name="letterman2" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">[selector1][selector2][selectorN]</div>
			<div class="content">
				<h1>[selector1][selector2][selectorN]</h1>
				<div class="desc">复合属性选择器，需要同时满足多个条件时使用。</div><hr />
				<div class="desc">Matches elements that have the specified attribute and it contains a certain value.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>selector1 </strong>(Selector) : 属性选择器</p>
				<p class="indent"><strong>selector2 </strong>(Selector) : 另一个属性选择器，用以进一步缩小范围</p>
				<p class="indent"><strong>selectorN </strong>(Selector) : 任意多个属性选择器</p>
				<h2>示例</h2>
				<p class="indent">
					找到所有含有 id 属性，并且它的 name 属性是以 man 结尾的 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input id="man-news" name="man-news" /&gt;<br/>
&lt;input name="milkman" /&gt;<br/>
&lt;input id="letterman" name="new-letterman" /&gt;<br/>
&lt;input name="newmilk" /&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[id][name$='man']") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input id="letterman" name="new-letterman" /&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">子元素</div>
		<div class="category">
			<div class="functionitem">:nth-child(index/even/odd/equation)</div>
			<div class="content">
				<h1>:nth-child(index/even/odd/equation)</h1>
				<div class="desc"><div>匹配其父元素下的第N个子或奇偶元素</div> <div class="longdesc"> ':eq(index)' 只匹配一个元素，而这个将为每一个父元素匹配子元素。:nth-child从1开始的，而:eq()是从0算起的！
				<p>可以使用:<br/>nth-child(even)<br/>
	:nth-child(odd)<br/>
	:nth-child(3n)<br/>
	:nth-child(2)<br/>
	:nth-child(3n+1)<br/>
	:nth-child(3n+2)</p>
</div><hr /><div>Matches the nth-child of its parent.</div> <div class="longdesc">While ':eq(index)' matches only a single element, this matches more then one: One for each parent. The specified index is one-indexed, in contrast to&nbsp;:eq() which starst at zero.</div>
</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>参数</h2>
				<p class="indent"><strong>index </strong>(Number) : 要匹配元素的序号，从1开始</p>
				<h2>示例</h2>
				<p class="indent">
					在每个 ul 查找第 2 个li
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;ul&gt;<br/>
&nbsp; &lt;li&gt;John&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Karl&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Brandon&lt;/li&gt;<br/>
&lt;/ul&gt;<br/>
&lt;ul&gt;<br/>
&nbsp; &lt;li&gt;Glen&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Tane&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Ralph&lt;/li&gt;<br/>
&lt;/ul&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("ul li:nth-child(2)") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;li&gt;Karl&lt;/li&gt;, &nbsp; &lt;li&gt;Tane&lt;/li&gt; ] 
				</div>
			</div>
			<div class="functionitem">:first-child</div>
			<div class="content">
				<h1>:first-child</h1>
				<div class="desc"><div>匹配第一个子元素</div> <div class="longdesc">':first' 只匹配一个元素，而此选择符将为每个父元素匹配一个子元素</div><hr /><div>Matches the first child of its parent.</div> <div class="longdesc">While ':first' matches only a single element, this matches more then one: One for each parent.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					在每个 ul 中查找第一个 li 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;ul&gt;<br/>
&nbsp; &lt;li&gt;John&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Karl&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Brandon&lt;/li&gt;<br/>
&lt;/ul&gt;<br/>
&lt;ul&gt;<br/>
&nbsp; &lt;li&gt;Glen&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Tane&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Ralph&lt;/li&gt;<br/>
&lt;/ul&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("ul li:first-child") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;li&gt;John&lt;/li&gt;, &lt;li&gt;Glen&lt;/li&gt; ] 
				</div>
			</div>
			<div class="functionitem">:last-child</div>
			<div class="content">
				<h1>:last-child</h1>
				<div class="desc"><div>匹配最后一个子元素</div> <div class="longdesc">':last'只匹配一个元素，而此选择符将为每个父元素匹配一个子元素</div><hr /><div>Matches the last child of its parent.</div> <div class="longdesc">While ':last' matches only a single element, this matches more then one: One for each parent.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					在每个 ul 中查找最后一个 li
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;ul&gt;<br/>
&nbsp; &lt;li&gt;John&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Karl&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Brandon&lt;/li&gt;<br/>
&lt;/ul&gt;<br/>
&lt;ul&gt;<br/>
&nbsp; &lt;li&gt;Glen&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Tane&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Ralph&lt;/li&gt;<br/>
&lt;/ul&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("ul li:last-child") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;li&gt;Brandon&lt;/li&gt;, &lt;li&gt;Ralph&lt;/li&gt; ] 
				</div>
			</div>
			<div class="functionitem">:only-child</div>
			<div class="content">
				<h1>:only-child</h1>
				<div class="desc"><div>如果某个元素是父元素中唯一的子元素，那将会被匹配</div> <div class="longdesc">如果父元素中含有其他元素，那将不会被匹配。</div><hr /><div>Matches the only child of its parent.</div> <div class="longdesc">If the parent has other child elements, nothing is matched.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					在 ul 中查找是唯一子元素的 li
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;ul&gt;<br/>
&nbsp; &lt;li&gt;John&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Karl&lt;/li&gt;<br/>
&nbsp; &lt;li&gt;Brandon&lt;/li&gt;<br/>
&lt;/ul&gt;<br/>
&lt;ul&gt;<br/>
&nbsp; &lt;li&gt;Glen&lt;/li&gt;<br/>
&lt;/ul&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("ul li:only-child") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;li&gt;Glen&lt;/li&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">表单</div>
		<div class="category">
			<div class="functionitem">:input</div>
			<div class="content">
				<h1>:input</h1>
				<div class="desc">匹配所有 input, textarea, select 和 button 元素</div><hr />
				<div class="desc">Matches all input, textarea, select and button elements.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有的input元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":input") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="text" /&gt;, &lt;input type="checkbox" /&gt;, &lt;input type="radio" /&gt;, &lt;input type="image" /&gt;, &lt;input type="file" /&gt;, &lt;input type="submit" /&gt;, &lt;input type="reset" /&gt;, &lt;input type="password" /&gt;, &lt;input type="button" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">:text</div>
			<div class="content">
				<h1>:text</h1>
				<div class="desc">匹配所有的单行文本框</div><hr /> <div class="desc">Matches all input elements of type text.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有文本框
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":text") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="text" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">:password</div>
			<div class="content">
				<h1>:password</h1>
				<div class="desc">匹配所有密码框</div> <hr /><div class="desc">Matches all input elements of type password.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有密码框 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":password") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="password" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">:radio</div>
			<div class="content">
				<h1>:radio</h1>
				<div class="desc">匹配所有单选按钮</div> <hr /> <div class="desc">Matches all input elements of type radio.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有单选按钮
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":radio") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="radio" /&gt; ]
				</div>
			</div>
			<div class="functionitem">:checkbox</div>
			<div class="content">
				<h1>:checkbox</h1>
				<div class="desc">匹配所有复选框</div><hr /> <div class="desc">Matches all input elements of type checkbox.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有复选框
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":checkbox") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="checkbox" /&gt; ]
				</div>
			</div>
			<div class="functionitem">:submit</div>
			<div class="content">
				<h1>:submit</h1>
				<div class="desc">匹配所有提交按钮</div><hr /><div class="desc">Matches all input elements of type submit.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有提交按钮
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":submit") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="submit" /&gt; ]
				</div>
			</div>
			<div class="functionitem">:image</div>
			<div class="content">
				<h1>:image</h1>
				<div class="desc">匹配所有图像域</div> <hr /><div class="desc">Matches all input elements of type image.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					匹配所有图像域
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":image") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="image" /&gt; ]
				</div>
			</div>
			<div class="functionitem">:reset</div>
			<div class="content">
				<h1>:reset</h1>
				<div class="desc">匹配所有重置按钮</div><hr />
				<div class="desc">Matches all input elements of type reset.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有重置按钮 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":reset") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="reset" /&gt; ]
				</div>
			</div>
			<div class="functionitem">:button</div>
			<div class="content">
				<h1>:button</h1>
				<div class="desc">匹配所有按钮</div><hr />
				<div class="desc">Matches all input elements of type button.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有按钮. 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":button") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="button" /&gt;,&lt;button&gt;&lt;/button&gt; ]
				</div>
			</div>
			<div class="functionitem">:file</div>
			<div class="content">
				<h1>:file</h1>
				<div class="desc">匹配所有文件域</div><hr />
				<div class="desc">Matches all input elements of type file.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有文件域
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" /&gt;<br/>
&nbsp; &lt;input type="checkbox" /&gt;<br/>
&nbsp; &lt;input type="radio" /&gt;<br/>
&nbsp; &lt;input type="image" /&gt;<br/>
&nbsp; &lt;input type="file" /&gt;<br/>
&nbsp; &lt;input type="submit" /&gt;<br/>
&nbsp; &lt;input type="reset" /&gt;<br/>
&nbsp; &lt;input type="password" /&gt;<br/>
&nbsp; &lt;input type="button" /&gt;<br/>
&nbsp; &lt;select&gt;&lt;option/&gt;&lt;/select&gt;<br/>
&nbsp; &lt;textarea&gt;&lt;/textarea&gt;<br/>
&nbsp; &lt;button&gt;&lt;/button&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":file") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="file" /&gt; ]
				</div>
			</div>
			<div class="functionitem">:hidden</div>
			<div class="content">
				<h1>:hidden</h1>
				<div class="desc">匹配所有不可见元素，或者type为hidden的元素</div><hr />
				<div class="desc">Matches all elements that are hidden, or input elements of type "hidden".</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找隐藏的 tr
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;table&gt;<br/>
&nbsp; &lt;tr style="display:none"&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt;<br/>
&nbsp; &lt;tr&gt;&lt;td&gt;Value 2&lt;/td&gt;&lt;/tr&gt;<br/>
&lt;/table&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("tr:hidden") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;tr style="display:none"&gt;&lt;td&gt;Value 1&lt;/td&gt;&lt;/tr&gt; ] 
				</div>
				<hr />
				<p class="indent">
					匹配type为hidden的元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="text" name="email" /&gt;<br/>
&nbsp; &lt;input type="hidden" name="id" /&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input:hidden") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="hidden" name="id" /&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">表单对象属性</div>
		<div class="category">
			<div class="functionitem">:enabled</div>
			<div class="content">
				<h1>:enabled</h1>
				<div class="desc">匹配所有可用元素</div><hr />
				<div class="desc">Matches all elements that are enabled.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有可用的input元素 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input name="email" disabled="disabled" /&gt;<br/>
&nbsp; &lt;input name="id" /&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input:enabled") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="id" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">:disabled</div>
			<div class="content">
				<h1>:disabled</h1>
				<div class="desc">匹配所有不可用元素</div><hr />
				<div class="desc">Matches all elements that are disabled.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有不可用的input元素  
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input name="email" disabled="disabled" /&gt;<br/>
&nbsp; &lt;input name="id" /&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input:disabled") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input name="email" disabled="disabled" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">:checked</div>
			<div class="content">
				<h1>:checked</h1>
				<div class="desc">匹配所有选中的被选中元素(复选框、单选框等，不包括select中的option)</div><hr />
				<div class="desc">Matches all elements that are checked.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有选中的复选框元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;<br/>
&nbsp; &lt;input type="checkbox" name="newsletter" checked="checked" value="Daily" /&gt;<br/>
&nbsp; &lt;input type="checkbox" name="newsletter" value="Weekly" /&gt;<br/>
&nbsp; &lt;input type="checkbox" name="newsletter" checked="checked" value="Monthly" /&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input:checked") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;input type="checkbox" name="newsletter" checked="checked" value="Daily" /&gt;, &lt;input type="checkbox" name="newsletter" checked="checked" value="Monthly" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">:selected</div>
			<div class="content">
				<h1>:selected</h1>
				<div class="desc">匹配所有选中的option元素</div><hr />
				<div class="desc">Matches all elements that are selected.</div>
				<h2>返回值</h2>
				<p class="indent">Array&lt;Element&gt;</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有选中的选项元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;select&gt;<br/>
&nbsp; &lt;option value="1"&gt;Flowers&lt;/option&gt;<br/>
&nbsp; &lt;option value="2" selected="selected"&gt;Gardens&lt;/option&gt;<br/>
&nbsp; &lt;option value="3"&gt;Trees&lt;/option&gt;<br/>
&lt;/select&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("select option:selected") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;option value="2" selected="selected"&gt;Gardens&lt;/option&gt; ] 
				</div>
			</div>
		</div>
	</div>
	<!-- Attributes -->
	<div class="menuitem">属性</div>
	<div class="functionmenu">
		<div class="categoryitem">属性</div>
		<div class="category">
			<div class="functionitem">attr(name)</div>
			<div class="content">
				<h1>attr(name)</h1>
				<div class="desc">取得第一个匹配元素的属性值。通过这个方法可以方便地从第一个匹配元素中获取一个属性的值。如果元素没有相应属性，则返回 undefined 。</div><hr />
				<div class="desc">Access a property on the first matched element. This method makes it easy to retrieve a property value from the first matched element. If the element does not have an attribute with such a name, undefined is returned.</div>
				<h2>返回值</h2>
				<p class="indent">Object</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) : 属性名称</p>
				<h2>示例</h2>
				<p class="indent">
					返回文档中第一个图像的src属性值。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;img src="test.jpg"/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").attr("src"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					test.jpg 
				</div>
			</div>
			<div class="functionitem">attr(properties)</div>
			<div class="content">
				<h1>attr(properties)</h1>
				<div class="desc"><div>将一个“名/值”形式的对象设置为所有匹配元素的属性。</div> <div class="longdesc">这是一种在所有匹配元素中批量设置很多属性的最佳方式。 注意，如果你要设置对象的class属性，你必须使用'className' 作为属性名。或者你可以直接使用.addClass( class ) 和 .removeClass( class ).</div></div><hr />
				<div class="desc"><div>Set a key/value object as properties to all matched elements.</div> <div class="longdesc">This serves as the best way to set a large number of properties on all matched elements. Note that you must use 'className' as key if you want to set the class-Attribute. Or use .addClass( class ) or .removeClass( class ).</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>properties </strong>(Map) : 作为属性的“名/值对”对象</p>
				<h2>示例</h2>
				<p class="indent">
					为所有图像设置src和alt属性。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;img/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").attr({ src: "test.jpg", alt: "Test Image" });
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;img src= "test.jpg"&nbsp; alt:="Test Image" /&gt; ] 
				</div>
				<hr />
			</div>
			<div class="functionitem">attr(key,value)</div>
			<div class="content">
				<h1>attr(key,value)</h1>
				<div class="desc">为所有匹配的元素设置一个属性值。</div><hr />
				<div class="desc">Set a single property to a value, on all matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>key </strong>(String) : 属性名称</p>
				<p class="indent"><strong>value </strong>(Object) : 属性值</p>
				<h2>示例</h2>
				<p class="indent">
					为所有图像设置src属性。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;img/&gt; <br/>
					&lt;img/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").attr("src","test.jpg"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;img src= "test.jpg" /&gt; , &lt;img src= "test.jpg" /&gt; ] 
				</div>
			</div>
			<div class="functionitem">attr(key,fn)</div>
			<div class="content">
				<h1>attr(key,fn)</h1>
				<div class="desc"><div>为所有匹配的元素设置一个计算的属性值。</div> <div class="longdesc">不提供值，而是提供一个函数，由这个函数计算的值作为属性值。</div></div><hr />
				<div class="desc"><div>Set a single property to a computed value, on all matched elements.</div> <div class="longdesc">Instead of supplying a string value as described 'above', a function is provided that computes the value.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>key </strong>(String) : 属性名称</p>
				<p class="indent"><strong>fn </strong>(Function) : 返回值的函数 范围:当前元素, 参数: 当前元素的索引值</p>
				<h2>示例</h2>
				<p class="indent">
					把src属性的值设置为title属性的值。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;img src="test.jpg"/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").attr("title", function() { return this.src });
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;img src="test.jpg" title="test.jpg" /&gt;
				</div>
			</div>
			<div class="functionitem">removeAttr(name)</div>
			<div class="content">
				<h1>removeAttr(name)</h1>
				<div class="desc">从每一个匹配的元素中删除一个属性</div><hr />
				<div class="desc">Remove an attribute from each of the matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) : 要删除的属性名</p>
				<h2>示例</h2>
				<p class="indent">
					将文档中图像的src属性删除 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;img src="test.jpg"/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").removeAttr("src"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;img /&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">CSS 类</div>
		<div class="category">
			<div class="functionitem">addClass(class)</div>
			<div class="content">
				<h1>addClass(class)</h1>
				<div class="desc">为每个匹配的元素添加指定的类名。</div><hr />
				<div class="desc">Adds the specified class(es) to each of the set of matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>class </strong>(String) : 一个或多个要添加到元素中的CSS类名，请用空格分开</p>
				<h2>示例</h2>
				<p class="indent">
					为匹配的元素加上 'selected' 类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").addClass("selected"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="selected"&gt;Hello&lt;/p&gt; ] 
				</div>
				<hr /><p class="indent">
					为匹配的元素加上 selected highlight 类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").addClass("selected highlight"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="selected highlight"&gt;Hello&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">removeClass(class)</div>
			<div class="content">
				<h1>removeClass(class)</h1>
				<div class="desc">从所有匹配的元素中删除全部或者指定的类。</div><hr />
				<div class="desc">Removes all or the specified class(es) from the set of matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>class </strong>(String) : (可选) 一个或多个要删除的CSS类名，请用空格分开</p>
				<h2>示例</h2>
				<p class="indent">
					从匹配的元素中删除 'selected' 类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p class="selected first"&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").removeClass("selected"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="first"&gt;Hello&lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					删除匹配元素的所有类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p class="selected first"&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").removeClass(); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;Hello&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">toggleClass(class)</div>
			<div class="content">
				<h1>toggleClass(class)</h1>
				<div class="desc">如果存在（不存在）就删除（添加）一个类。</div><hr />
				<div class="desc">Adds the specified class if it is not present, removes the specified class if it is present.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>class </strong>(String) :CSS类名</p>
				<h2>示例</h2>
				<p class="indent">
				  为匹配的元素切换 'selected' 类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p class="selected"&gt;Hello Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").toggleClass("selected"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="selected"&gt;Hello&lt;/p&gt;, &lt;p&gt;Hello Again&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">toggleClass( class, switch )</div>
			<div class="content">
				<h1>toggleClass( class, switch )</h1>
				<div class="desc">如果开关switch参数为true则加上对应的class，否则就删除。</div><hr />
				<div class="desc">Adds the specified class if it is not present, removes the specified class if it is present.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>class </strong>(String) :要切换的CSS类名</p>
				<p class="indent"><strong>switch </strong>(Boolean) :用于决定是否切换class的布尔值。</p>
				<h2>示例</h2>
				<p class="indent">
				  每点击三下加上一次 'selected' 类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
&nbsp;&nbsp;var count = 0;<br />
&nbsp;&nbsp;$("p").click(function(){<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(this).toggleClass("highlight", count++ % 3 == 0);<br />
&nbsp;&nbsp;});<br />
				</div>
			</div>
		</div>
		<div class="categoryitem">HTML代码</div>
		<div class="category">
			<div class="functionitem">html()</div>
			<div class="content">
				<h1>html()</h1>
				<div class="desc">取得第一个匹配元素的html内容。这个函数不能用于XML文档。但可以用于XHTML文档。</div><hr />
				<div class="desc">Get the html contents of the first matched element. This property is not available on XML documents (although it will work for XHTML documents).</div>
				<h2>返回值</h2>
				<p class="indent">String</p>
				<h2>示例</h2>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").html(); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;
				</div>
			</div>
			<div class="functionitem">html(val)</div>
			<div class="content">
				<h1>html(val)</h1>
				<div class="desc">设置每一个匹配元素的html内容。这个函数不能用于XML文档。但可以用于XHTML文档。</div><hr />
				<div class="desc">Set the html contents of every matched element. This property is not available on XML documents (although it will work for XHTML documents).</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>val </strong>(String) : 用于设定HTML内容的值</p>
				<h2>示例</h2>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").html("&lt;p&gt;Hello Again&lt;/p&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div&gt;&lt;p&gt;Hello Again&lt;/p&gt;&lt;/div&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">文本</div>
		<div class="category">
			<div class="functionitem">text()</div>
			<div class="content">
				<h1>text()</h1>
				<div class="desc"><div>取得所有匹配元素的内容。</div> <div class="longdesc">结果是由所有匹配元素包含的文本内容组合起来的文本。这个方法对HTML和XML文档都有效。</div></div><hr />
				<div class="desc"><div>Get the text contents of all matched elements.</div> <div class="longdesc">The result is a string that contains the combined text contents of all matched elements. This method works on both HTML and XML documents.</div></div>
				<h2>返回值</h2>
				<p class="indent">String</p>
				<h2>示例</h2>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;b&gt;Test&lt;/b&gt; Paragraph.&lt;/p&gt;&lt;p&gt;Paraparagraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").text(); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					Test Paragraph.Paraparagraph 
				</div>
			</div>
			<div class="functionitem">text(val)</div>
			<div class="content">
				<h1>text(val)</h1>
				<div class="desc"><div>设置所有匹配元素的文本内容</div> <div class="longdesc">与 html() 类似, 但将编码 HTML (将 "&lt;" 和 "&gt;" 替换成相应的HTML实体).</div></div><hr />
				<div class="desc"><div>Set the text contents of all matched elements.</div> <div class="longdesc">Similar to html(), but escapes HTML (replace "&lt;" and "&gt;" with their HTML entities).</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>val </strong>(String) : 用于设置元素内容的文本</p>
				<h2>示例</h2>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Test Paragraph.&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").text("&lt;b&gt;Some&lt;/b&gt; new text."); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;b&gt;Some&lt;/b&gt; new text.&lt;/p&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">值</div>
		<div class="category">
			<div class="functionitem">val()</div>
			<div class="content">
				<h1>val()</h1>
				<div class="desc"><div>获得第一个匹配元素的当前值。</div> <div class="longdesc">在 jQuery 1.2 中,可以返回任意元素的值了。包括select。如果多选，将返回一个数组，其包含所选的值。</div></div><hr />
				<div class="desc"><div>Get the content of the value attribute of the first matched element.</div> <div class="longdesc">In jQuery 1.2, a value is now returned for all elements, including selects. For multiple selects an array of values is returned.&nbsp;</div></div>
				<h2>返回值</h2>
				<p class="indent">String,Array</p>
				<h2>示例</h2>
				<p class="indent">
					获得单个select的值和多选select的值。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;/p&gt;&lt;br/&gt;<br/>
&lt;select id="single"&gt;<br/>
&nbsp; &lt;option&gt;Single&lt;/option&gt;<br/>
&nbsp; &lt;option&gt;Single2&lt;/option&gt;<br/>
&lt;/select&gt;<br/>
&lt;select id="multiple" multiple="multiple"&gt;<br/>
&nbsp; &lt;option selected="selected"&gt;Multiple&lt;/option&gt;<br/>
&nbsp; &lt;option&gt;Multiple2&lt;/option&gt;<br/>
&nbsp; &lt;option selected="selected"&gt;Multiple3&lt;/option&gt;<br/>
&lt;/select&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").append(<br/>
&nbsp; "&lt;b&gt;Single:&lt;/b&gt; "&nbsp;&nbsp; + $("#single").val() +<br/>
&nbsp; " &lt;b&gt;Multiple:&lt;/b&gt; " + $("#multiple").val().join(", ")<br/>
); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;b&gt;Single:&lt;/b&gt;Single&lt;b&gt;Multiple:&lt;/b&gt;Multiple, Multiple3&lt;/p&gt;] 
				</div>
				<hr />
				<p class="indent">
					获取文本框中的值 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input type="text" value="some text"/&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input").val();
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					some text 
				</div>
			</div>
			<div class="functionitem">val(val)</div>
			<div class="content">
				<h1>val(val)</h1>
				<div class="desc"><div>设置每一个匹配元素的值。</div> <div class="longdesc">在 jQuery 1.2, 这也可以为select元件赋值</div></div><hr />
				<div class="desc"><div>Set the value attribute of every matched element.</div> <div class="longdesc">In jQuery 1.2, this is also able to set the value of select elements, but selecting the appropriate options.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>val </strong>(String) : 要设置的值。</p>
				<h2>示例</h2>
				<p class="indent">
					设定文本框的值 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;input type="text"/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input").val("hello world!"); 
				</div>
			</div>
			<div class="functionitem">val(val)</div>
			<div class="content">
				<h1>val(val)</h1>
				<div class="desc">check,select,radio等都能使用为之赋值</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>val </strong>(Array&lt;String&gt;) : 用于 check/select 的值</p>
				<h2>示例</h2>
				<p class="indent">
					设定一个select和一个多选的select的值
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;select id="single"&gt;<br/>
&nbsp; &lt;option&gt;Single&lt;/option&gt;<br/>
&nbsp; &lt;option&gt;Single2&lt;/option&gt;<br/>
&lt;/select&gt;<br/>
&lt;select id="multiple" multiple="multiple"&gt;<br/>
&nbsp; &lt;option selected="selected"&gt;Multiple&lt;/option&gt;<br/>
&nbsp; &lt;option&gt;Multiple2&lt;/option&gt;<br/>
&nbsp; &lt;option selected="selected"&gt;Multiple3&lt;/option&gt;<br/>
&lt;/select&gt;&lt;br/&gt;<br/>
&lt;input type="checkbox" value="check1"/&gt; check1<br/>
&lt;input type="checkbox" value="check2"/&gt; check2<br/>
&lt;input type="radio" value="radio1"/&gt; radio1<br/>
&lt;input type="radio" value="radio2"/&gt; radio2 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#single").val("Single2");<br/>
$("#multiple").val(["Multiple2", "Multiple3"]);<br/>
$("input").val(["check2", "radio1"]);<br/>
				</div>
			</div>
		</div>
	</div>
	<!-- Traversing -->
	<div class="menuitem">筛选</div>
	<div class="functionmenu">
		<div class="categoryitem">过滤</div>
		<div class="category">
			<div class="functionitem">eq(index)</div>
			<div class="content">
				<h1>eq(index)</h1>
				<div class="desc"><div>获取第N个元素</div> <div class="longdesc">这个元素的位置是从0算起。</div></div><hr />
				<div class="desc"><div>Reduce the set of matched elements to a single element.</div> <div class="longdesc">The position of the element in the set of matched elements starts at 0 and goes to length - 1.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>index </strong>(Integer) :元素在jQuery对象中的索引</p>
				<h2>示例</h2>
				<p class="indent">
					获取匹配的第二个元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt; This is just a test.&lt;/p&gt; &lt;p&gt; So is this&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
$("p").eq(1)
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt; So is this&lt;/p&gt; ]
				</div>

			</div>
			<div class="functionitem">hasClass(class)</div>
			<div class="content">
				<h1>hasClass(class)</h1>
				<div class="desc"><div>检查当前的元素是否含有某个特定的类，如果有，则返回true。</div> <div class="longdesc">这其实就是 is("." + class)。</div></div><hr />
				<div class="desc"><div>Checks the current selection against a class and returns true, if at least one element of the selection has the given class.</div> <div class="longdesc">This is an alternative to is("." + class).</div></div>
				<h2>返回值</h2>
				<p class="indent">Boolean</p>
				<h2>参数</h2>
				<p class="indent"><strong>class </strong>(String) : 用于匹配的类名</p>
				<h2>示例</h2>
				<p class="indent">
					给包含有某个类的元素进行一个动画。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div class="protected"&gt;&lt;/div&gt;&lt;div&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").click(function(){<br/>
&nbsp; if ( $(this).hasClass("protected") )<br/>
&nbsp;&nbsp;&nbsp; $(this)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .animate({ left: -10 })<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .animate({ left: 10 })<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .animate({ left: -10 })<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .animate({ left: 10 })<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .animate({ left: 0 });<br/>
});
				</div>
			</div>
			<div class="functionitem">filter(expr)</div>
			<div class="content">
				<h1>filter(expr)</h1>
				<div class="desc"><div>筛选出与指定表达式匹配的元素集合。</div> <div class="longdesc">这个方法用于缩小匹配的范围。用逗号分隔多个表达式</div></div><hr />
				<div class="desc"><div>Removes all elements from the set of matched elements that do not match the specified expression(s).</div> <div class="longdesc">This method is used to narrow down the results of a search. Provide a comma-separated list of expressions to apply multiple filters at once.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(Expression) : 表达式</p>
				<h2>示例</h2>
				<p class="indent">
					保留带有select类的元素 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;Hello Again&lt;/p&gt;&lt;p class="selected"&gt;And Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").filter(".selected") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="selected"&gt;And Again&lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					保留第一个以及带有select类的元素 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;Hello Again&lt;/p&gt;&lt;p class="selected"&gt;And Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").filter(".selected, :first") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;Hello&lt;/p&gt;, &lt;p class="selected"&gt;And Again&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">filter(fn)</div>
			<div class="content">
				<h1>filter(fn)</h1>
				<div class="desc"><div>筛选出与指定函数返回值匹配的元素集合</div> <div class="longdesc">这个函数内部将对每个对象计算一次 (正如 '$.each'). 如果调用的函数返回false则这个元素被删除，否则就会保留。</div></div><hr />
				<div class="desc"><div>Removes all elements from the set of matched elements that does not match the specified function.</div> <div class="longdesc">The function is called with a context equal to the current element (just like '$.each'). If the function returns false, then the element is removed - anything else and the element is kept.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 传递进filter的函数</p>
				<h2>示例</h2>
				<p class="indent">
					保留子元素中不含有ol的元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;ol&gt;&lt;li&gt;Hello&lt;/li&gt;&lt;/ol&gt;&lt;/p&gt;&lt;p&gt;How are you?&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").filter(function(index) {<br/>
&nbsp; return $("ol", this).length == 0;<br/>
});
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;How are you?&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">is(expr)</div>
			<div class="content">
				<h1>is(expr)</h1>
				<div class="desc"><div>用一个表达式来检查当前选择的元素集合，如果其中至少有一个元素符合这个给定的表达式就返回true。</div> <div class="longdesc">如果没有元素符合，或者表达式无效，都返回'false'。 <strong>注意：</strong>在jQuery 1.3中对所有表达式提供了支持。先前版本中如果提供了复杂的表达式，比如层级选择器（比如 + , ~ 和 > ）始终会返回true</div></div><hr />
				<div class="desc"><div>Checks the current selection against an expression and returns true, if at least one element of the selection fits the given expression.</div> <div class="longdesc">If no element fits, or the expression is not valid, then the response will be 'false'.<strong>Note</strong>: As of jQuery 1.3 all expressions are supported. Previously complex expressions, such as those containing hierarchy selectors (such as +, ~, and >), always returned 'true'. </div></div>
				<h2>返回值</h2>
				<p class="indent">Boolean</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) :用于筛选的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					由于input元素的父元素是一个表单元素，所以返回true。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;form&gt;&lt;input type="checkbox" /&gt;&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[type='checkbox']").parent().is("form") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					true 
				</div>
			</div>
			<div class="functionitem">map(callback)</div>
			<div class="content">
				<h1>map(callback)</h1>
				<div class="desc"><div>将一组元素转换成其他数组（不论是否是元素数组）</div> <div class="longdesc">你可以用这个函数来建立一个列表，不论是值、属性还是CSS样式，或者其他特别形式。这都可以用'$.map()'来方便的建立。</div></div><hr />
				<div class="desc"><div>Translate a set of elements into another set of values (which may, or may not, be elements).</div> <div class="longdesc">You could use this to build lists of values, attributes, css values - or even perform special, custom, selector transformations. This is provided as a convenience method for using '$.map()'.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 给每个元素执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					把form中的每个input元素的值建立一个列表。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;b&gt;Values: &lt;/b&gt;&lt;/p&gt;<br/>
&lt;form&gt;<br/>
&nbsp; &lt;input type="text" name="name" value="John"/&gt;<br/>
&nbsp; &lt;input type="text" name="password" value="password"/&gt;<br/>
&nbsp; &lt;input type="text" name="url" value="http://ejohn.org/"/&gt;<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").append( $("input").map(function(){<br/>
&nbsp; return $(this).val();<br/>
}).get().join(", ") ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;John, password, http://ejohn.org/&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">not(expr)</div>
			<div class="content">
				<h1>not(expr)</h1>
				<div class="desc">删除与指定表达式匹配的元素</div><hr />
				<div class="desc">Removes elements matching the specified expression from the set of matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String, DOMElement, Array&lt;DOMElement&gt;) : 一个表达式、一个元素或者一组元素</p>
				<h2>示例</h2>
				<p class="indent">
					从p元素中删除带有 select 的ID的元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p id="selected"&gt;Hello Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").not( $("#selected")[0] ) 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;Hello&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">slice(start,end)</div>
			<div class="content">
				<h1>slice(start,[end])</h1>
				<div class="desc"><div>选取一个匹配的子集</div> <div class="longdesc">与原来的slice方法类似</div></div><hr />
				<div class="desc"><div>Selects a subset of the matched elements.</div> <div class="longdesc">Behaves exactly like the built-in Array slice method.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>start </strong>(Integer) :开始选取子集的位置。第一个元素是0.如果是负数，则可以从集合的尾部开始选起。</p>
				<p class="indent"><strong>end </strong>(Integer) : (可选) 结束选取自己的位置，如果不指定，则就是本身的结尾。</p>
				<h2>示例</h2>
				<p class="indent">
					选择第一个p元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slice(0, 1).wrapInner("&lt;b&gt;&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;b&gt;Hello&lt;/b&gt;&lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					选择前两个p元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slice(0, 2).wrapInner("&lt;b&gt;&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;b&gt;Hello&lt;/b&gt;&lt;/p&gt;,&lt;p&gt;&lt;b&gt;cruel&lt;/b&gt;&lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					只选取第二个p元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slice(1, 2).wrapInner("&lt;b&gt;&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;b&gt;cruel&lt;/b&gt;&lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					只选取第二第三个p元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slice(1).wrapInner("&lt;b&gt;&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;b&gt;cruel&lt;/b&gt;&lt;/p&gt;, &lt;p&gt;&lt;b&gt;World&lt;/b&gt;&lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					选取第最后一个p元素 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slice(-1).wrapInner("&lt;b&gt;&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;b&gt;World&lt;/b&gt;&lt;/p&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">查找</div>
		<div class="category">
			<div class="functionitem">add(expr)</div>
			<div class="content">
				<h1>add(expr)</h1>
				<div class="desc">把与表达式匹配的元素添加到jQuery对象中。这个函数可以用于连接分别与两个表达式匹配的元素结果集。</div>  <hr />
				<div class="desc">Adds more elements, matched by the given expression, to the set of matched elements.</div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String, DOMElement, Array&lt;DOMElement&gt;) : 用于匹配元素并添加的表达式字符串，或者用于动态生成的HTML代码，如果是一个字符串数组则返回多个元素</p>
				<h2>示例</h2>
				<p class="indent">
					添加一个新元素到一组匹配的元素中，并且这个新元素能匹配给定的表达式。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;span&gt;Hello Again&lt;/span&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").add("span") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;Hello&lt;/p&gt;, &lt;span&gt;Hello Again&lt;/span&gt; ] 
				</div>
				<hr />
				<p class="indent">
					动态生成一个元素并添加至匹配的元素中
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").add("&lt;span&gt;Again&lt;/span&gt;") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;Hello&lt;/p&gt;, &lt;span&gt;Hello Again&lt;/span&gt; ] 
				</div>
				<hr />
				<p class="indent">
					为匹配的元素添加一个或者多个元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;&lt;span id="a"&gt;Hello Again&lt;/span&gt;&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").add(document.getElementById("a")) 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;Hello&lt;/p&gt;, &lt;p&gt;&lt;span id="a"&gt;Hello Again&lt;/span&gt;&lt;/p&gt;, &lt;span id="a"&gt;Hello Again&lt;/span&gt; ] 
				</div>
			</div>
			<div class="functionitem">children(expr)</div>
			<div class="content">
				<h1>children([expr])</h1>
				<div class="desc"><div>取得一个包含匹配的元素集合中每一个元素的所有子元素的元素集合。</div> <div class="longdesc">可以通过可选的表达式来过滤所匹配的子元素。注意：parents()将查找所有祖辈元素，而children()只考虑子元素而不考虑所有后代元素。</div></div>  <hr />
				<div class="desc"><div>Get a set of elements containing all of the unique children of each of the matched set of elements.</div> <div class="longdesc">This set can be filtered with an optional expression that will cause only elements matching the selector to be collected. Also note: while parents() will look at all ancestors, children() will only consider immediate child elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选) 用以过滤子元素的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					查找DIV中的每个子元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;div&gt;&lt;span&gt;Hello Again&lt;/span&gt;&lt;/div&gt;&lt;p&gt;And Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").children() 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;span&gt;Hello Again&lt;/span&gt; ] 
				</div>
				<hr />
				<p class="indent">
					在每个div中查找 .selected 的类。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;span&gt;Hello&lt;/span&gt;&lt;p class="selected"&gt;Hello Again&lt;/p&gt;&lt;p&gt;And Again&lt;/p&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").children(".selected") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="selected"&gt;Hello Again&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">closest( [expr] )</div>
			<div class="content">
				<h1>closest( [expr] )</h1>
				<div class="desc"><div>jQuery 1.3新增。从元素本身开始，逐级向上级元素匹配，并返回最先匹配的元素。</div> <div class="longdesc"><p>closest会首先检查当前元素是否匹配，如果匹配则直接返回元素本身。如果不匹配则向上查找父元素，一层一层往上，直到找到匹配选择器的元素。如果什么都没找到则返回一个空的jQuery对象。</p><p>closest对于处理事件委派非常有用。</p></div></div>  <hr />
				<div class="desc"><div>New in jQuery 1.3 Get a set of elements containing the closest parent element that matches the specified selector, the starting element included.</div> <div class="longdesc">Closest works by first looking at the current element to see if it matches the specified expression, if so it just returns the element itself. If it doesn't match then it will continue to traverse up the document, parent by parent, until an element is found that matches the specified expression. If no matching element is found then none will be returned.

Closest is especially useful for dealing with event delegation.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选) 用以过滤元素的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					展示如何使用clostest来完成事件委派。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
&lt;ul&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;&lt;b&gt;Click me!&lt;/b&gt;&lt;/li&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;You can also &lt;b&gt;Click me!&lt;/b&gt;&lt;/li&gt;<br />
&lt;/ul&gt;<br />
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
$(document).bind("click", function (e) {<br />
&nbsp;&nbsp;&nbsp;&nbsp;$(e.target).closest("li").toggleClass("hilight");<br />
}); <br />
				</div>
			</div>
			<div class="functionitem">contents()</div>
			<div class="content">
				<h1>contents()</h1>
				<div class="desc">查找匹配元素内部所有的子节点（包括文本节点）。如果元素是一个iframe，则查找文档内容</div>  <hr />
				<div class="desc">Find all the child nodes inside the matched elements (including text nodes), or the content document, if the element is an iframe.</div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					查找所有文本节点并加粗
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello &lt;a href="http://ejohn.org/"&gt;John&lt;/a&gt;, how are you doing?&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").contents().not("[nodeType=1]").wrap("&lt;b/&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;b&gt;Hello&lt;/b&gt; &lt;a href="http://ejohn.org/"&gt;John&lt;/a&gt;, &lt;b&gt;how are you doing?&lt;/b&gt;&lt;/p&gt; 
				</div>
				<hr />
				<p class="indent">
					往一个空框架中加些内容
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;iframe src="/index-blank.html" width="300" height="100"&gt;&lt;/iframe&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("iframe").contents().find("body")
&nbsp; .append("I'm in an iframe!"); 
				</div>
			</div>
			<div class="functionitem">find(expr)</div>
			<div class="content">
				<h1>find(expr)</h1>
				<div class="desc"><div>搜索所有与指定表达式匹配的元素。这个函数是找出正在处理的元素的后代元素的好方法。</div> <div class="longdesc">所有搜索都依靠jQuery表达式来完成。这个表达式可以使用CSS1-3的选择器语法来写。</div></div>  <hr />
				<div class="desc"><div>Searches for all elements that match the specified expression. This method is a good way to find additional descendant elements with which to process.</div> <div class="longdesc">All searching is done using a jQuery expression. The expression can be written using CSS 1-3 Selector syntax.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) :用于查找的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					从所有的段落开始，进一步搜索下面的span元素。与$("p span")相同。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;span&gt;Hello&lt;/span&gt;, how are you?&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").find("span") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;span&gt;Hello&lt;/span&gt; ] 
				</div>
			</div>
			<div class="functionitem">next(expr)</div>
			<div class="content">
				<h1>next([expr])</h1>
				<div class="desc"><div>取得一个包含匹配的元素集合中每一个元素紧邻的后面同辈元素的元素集合。</div> <div class="longdesc">这个函数只返回后面那个紧邻的同辈元素，而不是后面所有的同辈元素（可以使用nextAll）。可以用一个可选的表达式进行筛选。</div></div>  <hr />
				<div class="desc"><div>Get a set of elements containing the unique next siblings of each of the matched set of elements.</div> <div class="longdesc">It only returns the very next sibling for each element, not all next siblings (nor does it return the next closest sibling). You may provide an optional expression to filter the match.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选) 用于筛选的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					找到每个段落的后面紧邻的同辈元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;Hello Again&lt;/p&gt;&lt;div&gt;&lt;span&gt;And Again&lt;/span&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").next() 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;Hello Again&lt;/p&gt;, &lt;div&gt;&lt;span&gt;And Again&lt;/span&gt;&lt;/div&gt; ] 
				</div>
				<hr />
				<p class="indent">
					找到每个段落的后面紧邻的同辈元素中类名为selected的元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p class="selected"&gt;Hello Again&lt;/p&gt;&lt;div&gt;&lt;span&gt;And Again&lt;/span&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").next(".selected") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="selected"&gt;Hello Again&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">nextAll(expr)</div>
			<div class="content">
				<h1>nextAll([expr])</h1>
				<div class="desc"><div>查找当前元素之后所有的同辈元素。</div> <div class="longdesc">可以用表达式过滤</div></div>  <hr />
				<div class="desc"><div>Find all sibling elements after the current element.</div> <div class="longdesc">Use an optional expression to filter the matched set.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选)用来过滤的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					给第一个div之后的所有元素加个类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;/div&gt;&lt;div&gt;&lt;/div&gt;&lt;div&gt;&lt;/div&gt;&lt;div&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div:first").nextAll().addClass("after"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div class="after"&gt;&lt;/div&gt;, &lt;div class="after"&gt;&lt;/div&gt;, &lt;div class="after"&gt;&lt;/div&gt; ] 
				</div>
			</div>
			<div class="functionitem">offsetParent()</div>
			<div class="content">
				<h1>offsetParent()</h1>
				<div class="desc"><div>返回第一个匹配元素用于定位的父节点。</div> <div class="longdesc">这返回父元素中第一个其position设为relative或者absolute的元素。此方法仅对可见元素有效。</div></div>  <hr />
				<div class="desc"><div>Returns a jQuery collection with the positioned parent of the first matched element.</div> <div class="longdesc">This is the first parent of the element that has position (as in relative or absolute). This method only works with visible elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
			</div>
			<div class="functionitem">parent(expr)</div>
			<div class="content">
				<h1>parent([expr])</h1>
				<div class="desc"><div>取得一个包含着所有匹配元素的唯一父元素的元素集合。</div> <div class="longdesc">你可以使用可选的表达式来筛选。</div></div>  <hr />
				<div class="desc"><div>Get a set of elements containing the unique parents of the matched set of elements.</div> <div class="longdesc">You may use an optional expression to filter the set of parent elements that will match.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选)用来筛选的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					查找每个段落的父元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").parent() 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;/div&gt;] 
				</div>
				<hr />
				<p class="indent">
					查找段落的父元素中每个类名为selected的父元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;/div&gt;&lt;div class="selected"&gt;&lt;p&gt;Hello Again&lt;/p&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").parent(".selected") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div class="selected"&gt;&lt;p&gt;Hello Again&lt;/p&gt;&lt;/div&gt; ] 
				</div>
			</div>
			<div class="functionitem">parents(expr)</div>
			<div class="content">
				<h1>parents([expr])</h1>
				<div class="desc">取得一个包含着所有匹配元素的祖先元素的元素集合（不包含根元素）。可以通过一个可选的表达式进行筛选。</div>  <hr />
				<div class="desc">Get a set of elements containing the unique ancestors of the matched set of elements (except for the root element). The matched elements can be filtered with an optional expression.</div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选) 用于筛选祖先元素的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					找到每个span元素的所有祖先元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;html&gt;&lt;body&gt;&lt;div&gt;&lt;p&gt;&lt;span&gt;Hello&lt;/span&gt;&lt;/p&gt;&lt;span&gt;Hello Again&lt;/span&gt;&lt;/div&gt;&lt;/body&gt;&lt;/html&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("span").parents() 
				</div>
				<p class="indent">
					找到每个span的所有是p元素的祖先元素。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("span").parents("p") 
				</div>
			</div>
			<div class="functionitem">prev(expr)</div>
			<div class="content">
				<h1>prev([expr])</h1>
				<div class="desc"><div>取得一个包含匹配的元素集合中每一个元素紧邻的前一个同辈元素的元素集合。</div> <div class="longdesc">可以用一个可选的表达式进行筛选。只有紧邻的同辈元素会被匹配到，而不是前面所有的同辈元素。</div></div>  <hr />
				<div class="desc"><div>Get a set of elements containing the unique previous siblings of each of the matched set of elements.</div> <div class="longdesc">Use an optional expression to filter the matched set. Only the immediately previous sibling is returned, not all previous siblings.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选) 用于筛选前一个同辈元素的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					找到每个段落紧邻的前一个同辈元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;div&gt;&lt;span&gt;Hello Again&lt;/span&gt;&lt;/div&gt;&lt;p&gt;And Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").prev() 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div&gt;&lt;span&gt;Hello Again&lt;/span&gt;&lt;/div&gt; ] 
				</div>
				<hr />
				<p class="indent">
					找到每个段落紧邻的前一个同辈元素中类名为selected的元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;span&gt;Hello&lt;/span&gt;&lt;/div&gt;&lt;p class="selected"&gt;Hello Again&lt;/p&gt;&lt;p&gt;And Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").prev(".selected") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="selected"&gt;Hello Again&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">prevAll(expr)</div>
			<div class="content">
				<h1>prevAll([expr])</h1>
				<div class="desc"><div>查找当前元素之前所有的同辈元素</div> <div class="longdesc">可以用表达式过滤。</div></div>  <hr />
				<div class="desc"><div>Find all sibling elements before the current element.</div> <div class="longdesc">Use an optional expression to filter the matched set.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选) 用于过滤的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					给最后一个之前的所有div加上一个类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;/div&gt;&lt;div&gt;&lt;/div&gt;&lt;div&gt;&lt;/div&gt;&lt;div&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div:last").prevAll().addClass("before"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;div class="before"&gt;&lt;/div&gt;, &lt;div class="before"&gt;&lt;/div&gt;, &lt;div class="before"&gt;&lt;/div&gt; ] 
				</div>
			</div>
			<div class="functionitem">siblings(expr)</div>
			<div class="content">
				<h1>siblings([expr])</h1>
				<div class="desc">取得一个包含匹配的元素集合中每一个元素的所有唯一同辈元素的元素集合。可以用可选的表达式进行筛选。</div>  <hr />
				<div class="desc">Get a set of elements containing all of the unique siblings of each of the matched set of elements. Can be filtered with an optional expressions.</div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) : (可选) 用于筛选同辈元素的表达式</p>
				<h2>示例</h2>
				<p class="indent">
					找到每个div的所有同辈元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;div&gt;&lt;span&gt;Hello Again&lt;/span&gt;&lt;/div&gt;&lt;p&gt;And Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").siblings() 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;Hello&lt;/p&gt;, &lt;p&gt;And Again&lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					找到每个div的所有同辈元素中带有类名为selected的元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;span&gt;Hello&lt;/span&gt;&lt;/div&gt;&lt;p class="selected"&gt;Hello Again&lt;/p&gt;&lt;p&gt;And Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").siblings(".selected") 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p class="selected"&gt;Hello Again&lt;/p&gt; ] 
				</div>
			</div>
		</div>
		<div class="categoryitem">串联</div>
		<div class="category">
			<div class="functionitem">andSelf()</div>
			<div class="content">
				<h1>andSelf()</h1>
				<div class="desc"><div>加入先前所选的加入当前元素中</div> <div class="longdesc">对于筛选或查找后的元素，要加入先前所选元素时将会很有用。</div></div>  <hr />
				<div class="desc"><div>Add the previous selection to the current selection.</div> <div class="longdesc">Useful for traversing elements, and then adding something that was matched before the last traversion.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					选取所有div以及内部的p，并加上border类
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;p&gt;First Paragraph&lt;/p&gt;&lt;p&gt;Second Paragraph&lt;/p&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div").find("p").andSelf().addClass("border"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;div class="border"&gt;&lt;p class="border"&gt;First Paragraph&lt;/p&gt;&lt;p class="border"&gt;Second Paragraph&lt;/p&gt;&lt;/div&gt; 
				</div>
			</div>
			<div class="functionitem">end()</div>
			<div class="content">
				<h1>end()</h1>
				<div class="desc"><div>回到最近的一个"破坏性"操作之前。即，将匹配的元素列表变为前一次的状态。</div> <div class="longdesc">如果之前没有破坏性操作，则返回一个空集。所谓的"破坏性"就是指任何改变所匹配的jQuery元素的操作。这包括在 Traversing 中任何返回一个jQuery对象的函数--'add', 'andSelf', 'children', 'filter', 'find', 'map', 'next', 'nextAll', 'not', 'parent', 'parents', 'prev', 'prevAll', 'siblings' and 'slice'--再加上 Manipulation 中的 'clone'。</div></div>  <hr />
				<div class="desc"><div>Revert the most recent 'destructive' operation, changing the set of matched elements to its previous state (right before the destructive operation).</div> <div class="longdesc">If there was no destructive operation before, an empty set is returned. A 'destructive' operation is any operation that changes the set of matched jQuery elements, which means any Traversing function that returns a jQuery object - including 'add', 'andSelf', 'children', 'filter', 'find', 'map', 'next', 'nextAll', 'not', 'parent', 'parents', 'prev', 'prevAll', 'siblings' and slice - plus the 'clone' function (from Manipulation).</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					选取所有的p元素，查找并选取span子元素，然后再回过来选取p元素
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;span&gt;Hello&lt;/span&gt;,how are you?&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").find("span").end() 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;span&gt;Hello&lt;/span&gt; how are you?&lt;/p&gt; ] 
				</div>
			</div>
		</div>
	</div>
	<!-- Manipulation -->
	<div class="menuitem">文档处理</div>
	<div class="functionmenu">
		<div class="categoryitem">内部插入</div>
		<div class="category">
			<div class="functionitem">append(content)</div>
			<div class="content">
				<h1>append(content)</h1>
				<div class="desc"><div>向每个匹配的元素内部追加内容。</div> <div class="longdesc">这个操作与对指定的元素执行appendChild方法，将它们添加到文档中的情况类似。</div></div><hr />
				<div class="desc"><div>Append content to the inside of every matched element.</div> <div class="longdesc">This operation is similar to doing an appendChild to all the specified elements, adding them into the document.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String, Element, jQuery) : 要追加到目标中的内容</p>
				<h2>示例</h2>
				<p class="indent">
					向所有段落中追加一些HTML标记。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").append("&lt;b&gt;Hello&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;I would like to say: &lt;b&gt;Hello&lt;/b&gt;&lt;/p&gt; ] 
				</div>
			</div>
			<div class="functionitem">appendTo(content)</div>
			<div class="content">
				<h1>appendTo(content)</h1>
				<div class="desc"><div>把所有匹配的元素追加到另一个、指定的元素元素集合中。</div> <div class="longdesc">实际上，使用这个方法是颠倒了常规的$(A).append(B)的操作，即不是把B追加到A中，而是把A追加到B中。</div></div><hr />
				<div class="desc"><div>Append all of the matched elements to another, specified, set of elements.</div> <div class="longdesc">This operation is, essentially, the reverse of doing a regular $(A).append(B), in that instead of appending B to A, you're appending A to B.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String) :用于被追加的内容</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落追加到ID值为foo的元素中。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;div id="foo"&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").appendTo("#foo"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;div id="foo"&gt;&lt;p&gt;I would like to say: &lt;/p&gt;&lt;/div&gt; 
				</div>
			</div>
			<div class="functionitem">prepend(content)</div>
			<div class="content">
				<h1>prepend(content)</h1>
				<div class="desc"><div>向每个匹配的元素内部前置内容。</div> <div class="longdesc">这是向所有匹配元素内部的开始处插入内容的最佳方式。</div></div><hr />
				<div class="desc"><div>Prepend content to the inside of every matched element.</div> <div class="longdesc">This operation is the best way to insert elements inside, at the beginning, of all matched elements.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String, Element, jQuery) : 要插入到目标元素内部前端的内容</p>
				<h2>示例</h2>
				<p class="indent">
					向所有段落中前置一些HTML标记代码。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").prepend("&lt;b&gt;Hello&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;p&gt;&lt;b&gt;Hello&lt;/b&gt;I would like to say: &lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					将一个DOM元素前置入所有段落
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;<br/>
&lt;p&gt;I would like to say: &lt;/p&gt;<br/>
&lt;b class="foo"&gt;Hello&lt;/b&gt;<br/>
&lt;b class="foo"&gt;Good Bye&lt;/b&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").prepend( $(".foo")[0] ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;b class="foo"&gt;Hello&lt;/b&gt;I would like to say: &lt;/p&gt;<br/>
&lt;p&gt;&lt;b class="foo"&gt;Hello&lt;/b&gt;I would like to say: &lt;/p&gt;<br/>
&lt;b class="foo"&gt;Hello&lt;/b&gt;<br/>
&lt;b class="foo"&gt;Good Bye&lt;/b&gt; 
				</div>
				<hr />
				<p class="indent">
					向所有段落中前置一个jQuery对象(类似于一个DOM元素数组)。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;b&gt;Hello&lt;/b&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").prepend( $("b") ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;b&gt;Hello&lt;/b&gt;I would like to say: &lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">prependTo(content)</div>
			<div class="content">
				<h1>prependTo(content)</h1>
				<div class="desc"><div>把所有匹配的元素前置到另一个、指定的元素元素集合中。</div> <div class="longdesc">实际上，使用这个方法是颠倒了常规的$(A).prepend(B)的操作，即不是把B前置到A中，而是把A前置到B中。</div></div><hr />
				<div class="desc"><div>Prepend all of the matched elements to another, specified, set of elements.</div> <div class="longdesc">This operation is, essentially, the reverse of doing a regular $(A).prepend(B), in that instead of prepending B to A, you're prepending A to B.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String) :用于匹配元素的jQuery表达式</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落追加到ID值为foo的元素中。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;div id="foo"&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").prependTo("#foo"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;div id="foo"&gt;&lt;p&gt;I would like to say: &lt;/p&gt;&lt;/div&gt; 
				</div>
			</div>
		</div>
		<div class="categoryitem">外部插入</div>
		<div class="category">
			<div class="functionitem">after(content)</div>
			<div class="content">
				<h1>after(content)</h1>
				<div class="desc">在每个匹配的元素之后插入内容。</div><hr />
				<div class="desc">Insert content after each of the matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String, Element, jQuery) : 插入到每个目标后的内容</p>
				<h2>示例</h2>
				<p class="indent">
					在所有段落之后插入一些HTML标记代码。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").after("&lt;b&gt;Hello&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;b&gt;Hello&lt;/b&gt; 
				</div>
				<hr />
				<p class="indent">
					在所有段落之后插入一个DOM元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;b id="foo"&gt;Hello&lt;/b&gt;&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").after( $("#foo")[0] ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;b id="foo"&gt;Hello&lt;/b&gt; 
				</div>
				<hr />
				<p class="indent">
					在所有段落中后插入一个jQuery对象(类似于一个DOM元素数组)。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;b&gt;Hello&lt;/b&gt;&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").after( $("b") ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;b&gt;Hello&lt;/b&gt; 
				</div>
			</div>
			<div class="functionitem">before(content)</div>
			<div class="content">
				<h1>before(content)</h1>
				<div class="desc">在每个匹配的元素之前插入内容。</div><hr />
				<div class="desc">Insert content before each of the matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String, Element, jQuery) : 插入到每个目标前的内容</p>
				<h2>示例</h2>
				<p class="indent">
					在所有段落之前插入一些HTML标记代码。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").before("&lt;b&gt;Hello&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ &lt;b&gt;Hello&lt;/b&gt;&lt;p&gt;I would like to say: &lt;/p&gt; ] 
				</div>
				<hr />
				<p class="indent">
					在所有段落之前插入一个元素。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;b id="foo"&gt;Hello&lt;/b&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").before( $("#foo")[0] ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;b id="foo"&gt;Hello&lt;/b&gt;&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
				<hr />
				<p class="indent">
					在所有段落中前插入一个jQuery对象(类似于一个DOM元素数组)。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;b&gt;Hello&lt;/b&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").before( $("b") ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;b&gt;Hello&lt;/b&gt;&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">insertAfter(content)</div>
			<div class="content">
				<h1>insertAfter(content)</h1>
				<div class="desc"><div>把所有匹配的元素插入到另一个、指定的元素元素集合的后面。</div> <div class="longdesc">实际上，使用这个方法是颠倒了常规的$(A).after(B)的操作，即不是把B插入到A后面，而是把A插入到B后面。</div></div><hr />
				<div class="desc"><div>Insert all of the matched elements after another, specified, set of elements.</div> <div class="longdesc">This operation is, essentially, the reverse of doing a regular $(A).after(B), in that instead of inserting B after A, you're inserting A after B.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String) : 用于匹配元素的jQuery表达式</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落插入到一个元素之后。与 $("#foo").after("p")相同
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;div id="foo"&gt;Hello&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").insertAfter("#foo"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;div id="foo"&gt;Hello&lt;/div&gt;&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">insertBefore(content)</div>
			<div class="content">
				<h1>insertBefore(content)</h1>
				<div class="desc"><div>把所有匹配的元素插入到另一个、指定的元素元素集合的前面。</div> <div class="longdesc">实际上，使用这个方法是颠倒了常规的$(A).before(B)的操作，即不是把B插入到A前面，而是把A插入到B前面。</div></div><hr />
				<div class="desc"><div>Insert all of the matched elements before another, specified, set of elements.</div> <div class="longdesc">This operation is, essentially, the reverse of doing a regular $(A).before(B), in that instead of inserting B before A, you're inserting A before B.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String) : 用于匹配元素的jQuery表达式</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落插入到一个元素之前。与 $("#foo").before("p")相同。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div id="foo"&gt;Hello&lt;/div&gt;&lt;p&gt;I would like to say: &lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").insertBefore("#foo"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;I would like to say: &lt;/p&gt;&lt;div id="foo"&gt;Hello&lt;/div&gt; 
				</div>
			</div>
		</div>
		<div class="categoryitem">包裹</div>
		<div class="category">
			<div class="functionitem">wrap(html)</div>
			<div class="content">
				<h1>wrap(html)</h1>
				<div class="desc"><div>把所有匹配的元素用其他元素的结构化标记包裹起来。</div> <div class="longdesc">这种包装对于在文档中插入额外的结构化标记最有用，而且它不会破坏原始文档的语义品质。<p>这个函数的原理是检查提供的第一个元素（它是由所提供的HTML标记代码动态生成的），并在它的代码结构中找到最上层的祖先元素－－这个祖先元素就是包裹元素。</p>当HTML标记代码中的元素包含文本时无法使用这个函数。因此，如果要添加文本应该在包裹完成之后再行添加。</div></div><hr />
				<div class="desc"><div>Wrap all matched elements with a structure of other elements.</div> <div class="longdesc">This wrapping process is most useful for injecting additional structure into a document, without ruining the original semantic qualities of a document. <p>This works by going through the first element provided (which is generated, on the fly, from the provided HTML) and finds the deepest ancestor element within its structure -- it is that element that will enwrap everything else. </p> This does not work with elements that contain text. Any necessary text must be added after the wrapping is done.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>html </strong>(String) : HTML标记代码字符串，用于动态生成元素并包裹目标元素</p>
				<h2>示例</h2>
				<p class="indent">
					把所有的段落用一个新创建的div包裹起来
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Test Paragraph.&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").wrap("&lt;div class='wrap'&gt;&lt;/div&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;div class="wrap"&gt;&lt;p&gt;Test Paragraph.&lt;/p&gt;&lt;/div&gt; 
				</div>
			</div>
			<div class="functionitem">wrap(elem)</div>
			<div class="content">
				<h1>wrap(elem)</h1>
				<div class="desc">把所有匹配的元素用其他元素的结构化标记包装起来。</div><hr />
				<div class="desc">Wrap all matched elements with a structure of other elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>elem </strong>(Element) : 用于包装目标元素的DOM元素</p>
				<h2>示例</h2>
				<p class="indent">
					用ID是"content"的div将每一个段落包裹起来 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Test Paragraph.&lt;/p&gt;&lt;div id="content"&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").wrap(document.getElementById('content')); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;div id="content"&gt;&lt;p&gt;Test Paragraph.&lt;/p&gt;&lt;/div&gt;&lt;div id="content"&gt;&lt;/div&gt; 
				</div>
			</div>
			<div class="functionitem">wrapAll(html)</div>
			<div class="content">
				<h1>wrapAll(html)</h1>
				<div class="desc"><div>将所有匹配的元素用单个元素包裹起来</div> <div class="longdesc">这于 '.wrap()'<a href="http://docs.jquery.com/Manipulation/wrap" title="Manipulation/wrap"></a> 是不同的，'.wrap()'为每一个匹配的元素都包裹一次。<p>这种包装对于在文档中插入额外的结构化标记最有用，而且它不会破坏原始文档的语义品质。</p>这个函数的原理是检查提供的第一个元素并在它的代码结构中找到最上层的祖先元素－－这个祖先元素就是包装元素。</div></div><hr />
				<div class="desc"><div>Wrap all the elements in the matched set into a single wrapper element.</div> <div class="longdesc">This is different from '.wrap()'<a href="http://docs.jquery.com/Manipulation/wrap" title="Manipulation/wrap"></a> where each element in the matched set would get wrapped with an element. <p>This wrapping process is most useful for injecting additional structure into a document, without ruining the original semantic qualities of a document. </p>This works by going through the first element provided (which is generated, on the fly, from the provided HTML) and finds the deepest ancestor element within its structure -- it is that element that will enwrap everything else.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>html </strong>(String) : TML标记代码字符串，用于动态生成元素并包装目标元素</p>
				<h2>示例</h2>
				<p class="indent">
					用一个生成的div将所有段落包裹起来
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").wrapAll("&lt;div&gt;&lt;/div&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt;&lt;/div&gt; 
				</div>
			</div>
			<div class="functionitem">wrapAll(elem)</div>
			<div class="content">
				<h1>wrapAll(elem)</h1>
				<div class="desc"><div>将所有匹配的元素用单个元素包裹起来</div> <div class="longdesc">这于 '.wrap()'<a href="http://docs.jquery.com/Manipulation/wrap" title="Manipulation/wrap"></a> 是不同的，'.wrap()'为每一个匹配的元素都包裹一次。</div></div><hr />
				<div class="desc"><div>Wrap all the elements in the matched set into a single wrapper element.</div> <div class="longdesc">This is different from '.wrap()'<a href="http://docs.jquery.com/Manipulation/wrap" title="Manipulation/wrap"></a> where each element in the matched set would get wrapped with an element.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>elem </strong>(Element) : 用于包装目标元素的DOM元素</p>
				<h2>示例</h2>
				<p class="indent">
					用一个生成的div将所有段落包裹起来
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").wrapAll(document.createElement("div")); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;div&gt;&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt;&lt;/div&gt; 
				</div>
			</div>
			<div class="functionitem">wrapInner(html)</div>
			<div class="content">
				<h1>wrapInner(html)</h1>
				<div class="desc"><div>将每一个匹配的元素的子内容(包括文本节点)用一个HTML结构包裹起来</div> <div class="longdesc">这个函数的原理是检查提供的第一个元素（它是由所提供的HTML标记代码动态生成的），并在它的代码结构中找到最上层的祖先元素－－这个祖先元素就是包装元素。</div></div><hr />
				<div class="desc"><div>Wrap the inner child contents of each matched element (including text nodes) with an HTML structure.</div> <div class="longdesc">This wrapping process is most useful for injecting additional structure into a document, without ruining the original semantic qualities of a document. This works by going through the first element provided (which is generated, on the fly, from the provided HTML) and finds the deepest ancestor element within its structure -- it is that element that will enwrap everything else.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>html </strong>(String) : HTML标记代码字符串，用于动态生成元素并包装目标元素</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落内的每个子内容加粗
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").wrapInner("&lt;b&gt;&lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;b&gt;Hello&lt;/b&gt;&lt;/p&gt;&lt;p&gt;&lt;b&gt;cruel&lt;/b&gt;&lt;/p&gt;&lt;p&gt;&lt;b&gt;World&lt;/b&gt;&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">wrapInner(elem)</div>
			<div class="content">
				<h1>wrapInner(elem)</h1>
				<div class="desc">将每一个匹配的元素的子内容(包括文本节点)用DOM元素包裹起来</div><hr />
				<div class="desc">Wrap the inner child contents of each matched element (including text nodes) with a DOM element.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>elem </strong>(Element) : 用于包装目标元素的DOM元素</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落内的每个子内容加粗 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").wrapInner(document.createElement("b")); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;b&gt;Hello&lt;/b&gt;&lt;/p&gt;&lt;p&gt;&lt;b&gt;cruel&lt;/b&gt;&lt;/p&gt;&lt;p&gt;&lt;b&gt;World&lt;/b&gt;&lt;/p&gt; 
				</div>
			</div>
		</div>
		<div class="categoryitem">替换</div>
		<div class="category">
			<div class="functionitem">replaceWith(content)</div>
			<div class="content">
				<h1>replaceWith(content)</h1>
				<div class="desc">将所有匹配的元素替换成指定的HTML或DOM元素。</div><hr />
				<div class="desc">Replaces all matched elements with the specified HTML or DOM elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>content </strong>(String, Element, jQuery) : 用于将匹配元素替换掉的内容</p>
				<h2>示例</h2>
				<p class="indent">
					把所有的段落标记替换成加粗的标记。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").replaceWith("&lt;b&gt;Paragraph. &lt;/b&gt;"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;b&gt;Paragraph. &lt;/b&gt;&lt;b&gt;Paragraph. &lt;/b&gt;&lt;b&gt;Paragraph. &lt;/b&gt; 
				</div>
			</div>
			<div class="functionitem">replaceAll(selector)</div>
			<div class="content">
				<h1>replaceAll(selector)</h1>
				<div class="desc">用匹配的元素替换掉所有 selector匹配到的元素。</div><hr />
				<div class="desc">Replaces the elements matched by the specified selector with the matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>selector </strong>(选择器) : 用于查找所要被替换的元素</p>
				<h2>示例</h2>
				<p class="indent">
					把所有的段落标记替换成加粗标记
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;cruel&lt;/p&gt;&lt;p&gt;World&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("&lt;b&gt;Paragraph. &lt;/b&gt;").replaceAll("p"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;b&gt;Paragraph. &lt;/b&gt;&lt;b&gt;Paragraph. &lt;/b&gt;&lt;b&gt;Paragraph. &lt;/b&gt; 
				</div>
			</div>
		</div>
		<div class="categoryitem">删除</div>
		<div class="category">
			<div class="functionitem">empty()</div>
			<div class="content">
				<h1>empty()</h1>
				<div class="desc">删除匹配的元素集合中所有的子节点。</div><hr />
				<div class="desc">Remove all child nodes from the set of matched elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落的子元素（包括文本节点）删除
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello, &lt;span&gt;Person&lt;/span&gt; &lt;a href="#"&gt;and person&lt;/a&gt;&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").empty(); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">remove(expr)</div>
			<div class="content">
				<h1>remove([expr])</h1>
				<div class="desc"><div>从DOM中删除所有匹配的元素。</div> <div class="longdesc">这个方法不会把匹配的元素从jQuery对象中删除，因而可以在将来再使用这些匹配的元素。</div></div><hr />
				<div class="desc"><div>Removes all matched elements from the DOM.</div> <div class="longdesc">This does NOT remove them from the jQuery object, allowing you to use the matched elements further. Can be filtered with an optional expression.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>expr </strong>(String) :  (可选) 用于筛选元素的jQuery表达式</p>
				<h2>示例</h2>
				<p class="indent">
					从DOM中把所有段落删除
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt; how are &lt;p&gt;you?&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").remove(); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					how are 
				</div>
				<hr />
				<p class="indent">
					从DOM中把带有hello类的段落删除
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p class="hello"&gt;Hello&lt;/p&gt; how are &lt;p&gt;you?&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").remove(".hello"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					how are &lt;p&gt;you?&lt;/p&gt; 
				</div>
			</div>
		</div>
		<div class="categoryitem">复制</div>
		<div class="category">
			<div class="functionitem">clone()</div>
			<div class="content">
				<h1>clone()</h1>
				<div class="desc"><div>克隆匹配的DOM元素并且选中这些克隆的副本。</div> <div class="longdesc">在想把DOM文档中元素的副本添加到其他位置时这个函数非常有用。</div></div><hr />
				<div class="desc"><div>Clone matched DOM Elements and select the clones.</div> <div class="longdesc">This is useful for moving copies of the elements to another location in the DOM.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					克隆所有b元素（并选中这些克隆的副本），然后将它们前置到所有段落中。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;b&gt;Hello&lt;/b&gt;&lt;p&gt;, how are you?&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("b").clone().prependTo("p"); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;b&gt;Hello&lt;/b&gt;&lt;p&gt;&lt;b&gt;Hello&lt;/b&gt;, how are you?&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">clone(true)</div>
			<div class="content">
				<h1>clone(true)</h1>
				<div class="desc"><div>元素以及其所有的事件处理并且选中这些克隆的副本</div> <div class="longdesc">在想把DOM文档中元素的副本添加到其他位置时这个函数非常有用。</div></div><hr />
				<div class="desc"><div>Clone matched DOM Elements, and all their event handlers, and select the clones.</div> <div class="longdesc">This is useful for moving copies of the elements, and their events, to another location in the DOM.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>true </strong>(Boolean) : 设置为true以便复制元素的所有事件处理</p>
				<h2>示例</h2>
				<p class="indent">
					创建一个按钮，他可以复制自己，并且他的副本也有同样功能。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;button&gt;Clone Me!&lt;/button&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("button").click(function(){<br/>
&nbsp; $(this).clone(true).insertAfter(this);<br/>
});
				</div>
			</div>
		</div>
	</div>
	<!-- CSS -->
	<div class="menuitem">CSS</div>
	<div class="functionmenu">
		<div class="categoryitem">CSS</div>
		<div class="category">
			<div class="functionitem">css(name)</div>
			<div class="content">
				<h1>css(name)</h1>
				<div class="desc">访问第一个匹配元素的样式属性。</div> <hr />
				<div class="desc">Return a style property on the first matched element.</div> 
				<h2>返回值</h2>
				<p class="indent">String</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(String) : 要访问的属性名称</p>
				<h2>示例</h2>
				<p class="indent">
					取得第一个段落的color样式属性的值。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").css("color"); 
				</div>
			</div>
			<div class="functionitem">css(properties)</div>
			<div class="content">
				<h1>css(properties)</h1>
				<div class="desc"><div>把一个“名/值对”对象设置为所有匹配元素的样式属性。</div> <div class="longdesc">这是一种在所有匹配的元素上设置大量样式属性的最佳方式。</div></div>  <hr />
				<div class="desc"><div>Set a key/value object as style properties to all matched elements.</div> <div class="longdesc">This is the best way to set several style properties on all matched elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>properties </strong>(Map) : 要设置为样式属性的名/值对</p>
				<h2>示例</h2>
				<p class="indent">
					将所有段落的字体颜色设为红色并且背景为蓝色。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").css({ color: "#ff0011", background: "blue" });
				</div>
				<hr />
				<p class="indent">
					如果属性名包含 "-"的话，必须使用引号: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").css({ "margin-left": "10px", "background-color": "blue" });
				</div>
			</div>
			<div class="functionitem">css(name,value)</div>
			<div class="content">
				<h1>css(name,value)</h1>
				<div class="desc"><div>在所有匹配的元素中，设置一个样式属性的值。</div> <div class="longdesc">数字将自动转化为像素值</div></div>  <hr />
				<div class="desc"><div>Set a single style property to a value on all matched elements.</div> <div class="longdesc">If a number is provided, it is automatically converted into a pixel value.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>name </strong>(value) : 属性名</p>
				<p class="indent"><strong>value </strong>(String, Number) : 属性值</p>
				<h2>示例</h2>
				<p class="indent">
					将所有段落字体设为红色
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").css("color","red"); 
				</div>
			</div>
		</div>
		<div class="categoryitem">位置</div>
		<div class="category">
			<div class="functionitem">offset()</div>
			<div class="content">
				<h1>offset()</h1>
				<div class="desc"><div>获取匹配元素在当前视口的相对偏移。</div> <div class="longdesc">返回的对象包含两个整形属性：top 和 left。此方法只对可见元素有效。</div></div>  <hr />
				<div class="desc"><div>Get the current offset of the first matched element relative to the viewport.</div> <div class="longdesc">The returned object contains two Integer properties, top and left. The method works only with visible elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Object{top,left}</p>
				<h2>示例</h2>
				<p class="indent">
					获取第二段的偏移
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;2nd Paragraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var p = $("p:last");<br/>
var offset = p.offset();<br/>
p.html( "left: " + offset.left + ", top: " + offset.top ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;left: 0, top: 35&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">position()</div>
			<div class="content">
				<h1>position()</h1>
				<div class="desc"><div>获取匹配元素相对父元素的偏移。</div> <div class="longdesc">返回的对象包含两个整形属性：top 和 left。为精确计算结果，请在补白、边框和填充属性上使用像素单位。此方法只对可见元素有效。</div></div>
				<hr />
				<div class="desc"><div>Gets the top and left position of an element relative to its offset parent.</div> <div class="longdesc">The returned object contains two Integer properties, top and left. For accurate calculations make sure to use pixel values for margins, borders and padding. This method only works with visible elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Object{top,left}</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段的偏移
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;2nd Paragraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var p = $("p:first");<br/>
var position = p.position();<br/>
$("p:last").html( "left: " + position.left + ", top: " + position.top ); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;left: 15, top: 15&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">scrollTop()</div>
			<div class="content">
				<h1>scrollTop()</h1>
				<div class="desc"><div>获取匹配元素相对滚动条顶部的偏移。</div> <div class="longdesc">此方法对可见和隐藏元素均有效。</div></div>
				<hr />
				<div class="desc"><div>Gets the scroll top offset of the first matched element.</div> <div class="longdesc">This method works for both visible and hidden elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Integer</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段相对滚动条顶部的偏移
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;2nd Paragraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var p = $("p:first");<br/>
$("p:last").text( "scrollTop:" + p.scrollTop() );

				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;scrollTop: 0&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">scrollTop(val)</div>
			<div class="content">
				<h1>scrollTop(val)</h1>
				<div class="desc"><div>传递参数值时，设置滚动条顶部偏移为该值。</div> <div class="longdesc">此方法对可见和隐藏元素均有效。</div></div>
				<hr />
				<div class="desc"><div>When a value is passed in, the scroll top offset is set to that value on all matched elements.</div> <div class="longdesc">This method works for both visible and hidden elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					设置相对滚动条顶部的偏移
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div.demo").scrollTop(300);
				</div>
			</div>
			<div class="functionitem">scrollLeft()</div>
			<div class="content">
				<h1>scrollLeft()</h1>
				<div class="desc"><div>获取匹配元素相对滚动条左侧的偏移。</div> <div class="longdesc">此方法对可见和隐藏元素均有效。</div></div>
				<hr />
				<div class="desc"><div>Gets the scroll left offset of the first matched element.</div> <div class="longdesc">This method works for both visible and hidden elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Integer</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段相对滚动条左侧的偏移
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;2nd Paragraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var p = $("p:first");<br/>
$("p:last").text( "scrollLeft:" + p.scrollLeft() );

				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;scrollLeft: 0&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">scrollLeft(val)</div>
			<div class="content">
				<h1>scrollLeft(val)</h1>
				<div class="desc"><div>传递参数值时，设置滚动条左侧偏移为该值。</div> <div class="longdesc">此方法对可见和隐藏元素均有效。</div></div>
				<hr />
				<div class="desc"><div>When a value is passed in, the scroll left offset is set to that value on all matched elements.</div> <div class="longdesc">This method works for both visible and hidden elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					设置相对滚动条左侧的偏移
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("div.demo").scrollLeft(300);
				</div>
			</div>
		</div>
		<div class="categoryitem">尺寸</div>
		<div class="category">
			<div class="functionitem">height()</div>
			<div class="content">
				<h1>height()</h1>
				<div style="" id="overview" class="tabs-container">  <div class="desc"><div>取得第一个匹配元素当前计算的高度值（px）。</div> <div class="longdesc">在 jQuery 1.2 以后可以用来获取 window 和 document 的高</div></div> </div>  <hr />
				<div style="" id="overview" class="tabs-container">  <div class="desc"><div>Get the current computed, pixel, height of the first matched element.</div> <div class="longdesc">In jQuery 1.2, this method is able to find the height of the window and document.</div></div> </div>  
				<h2>返回值</h2>
				<p class="indent">Integer</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段的高
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").height(); 
				</div>
				<hr />
				<p class="indent">
					获取文档的高 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(document).height(); 
				</div>
			</div>
			<div class="functionitem">height(val)</div>
			<div class="content">
				<h1>height(val)</h1>
				<div class="desc"><div>为每个匹配的元素设置CSS高度(hidth)属性的值。如果没有明确指定单位（如：em或%），使用px。</div> <div class="longdesc">如果没有明确指定单位（如：em或%），使用px。</div></div>  <hr />
				<div class="desc"><div>Set the CSS height of every matched element.</div> <div class="longdesc">If no explicit unit was specified (like 'em' or '%') then "px" is concatenated to the value.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>val </strong>(String, Number) : 设定CSS中 'height' 的值</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落的高设为 20: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").height(20); 
				</div>
			</div>
			<div class="functionitem">width()</div>
			<div class="content">
				<h1>width()</h1>
				<div>  <div class="desc"><div>取得第一个匹配元素当前计算的宽度值（px）。</div> <div class="longdesc">在 jQuery 1.2 以后可以用来获取 window 和 document 的宽</div></div> </div>  <hr />
				<div>  <div class="desc"><div>Get the current computed, pixel, width of the first matched element.</div> <div class="longdesc">In jQuery 1.2, this method is able to find the width of the window and document.</div></div> </div>  
				<h2>返回值</h2>
				<p class="indent">Integer</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段的宽
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").width(); 
				</div>
				<hr />
				<p class="indent">
					获取当前窗口的宽
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(window).width(); 
				</div>
			</div>
			<div class="functionitem">width(val)</div>
			<div class="content">
				<h1>width(val)</h1>
				<div class="desc"><div>为每个匹配的元素设置CSS宽度(width)属性的值。</div> <div class="longdesc">如果没有明确指定单位（如：em或%），使用px。</div></div> <hr /> 
				<div class="desc"><div>Set the CSS width of every matched element.</div> <div class="longdesc">If no explicit unit was specified (like 'em' or '%') then "px" is concatenated to the value.</div></div>  
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>val </strong>(String, Number) : 设定 CSS 'width' 的属性值</p>
				<h2>示例</h2>
				<p class="indent">
					将所有段落的宽设为 20: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").width(20); 
				</div>
			</div>
			<div class="functionitem">innerHeight()</div>
			<div class="content">
				<h1>innerHeight()</h1>
				<div class="desc"><div>获取第一个匹配元素内部区域高度（包括补白、不包括边框）。</div> <div class="longdesc">此方法对可见和隐藏元素均有效。</div></div>
				<hr />
				<div class="desc"><div>Gets the inner height (excludes the border and includes the padding) for the first matched element.</div> <div class="longdesc">This method works for both visible and hidden elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Integer</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段落内部区域高度。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;2nd Paragraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var p = $("p:first");<br/>
$("p:last").text( "innerHeight:" + p.innerHeight() );
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;innerHeight: 16&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">innerWidth()</div>
			<div class="content">
				<h1>innerHeight()</h1>
				<div class="desc"><div>获取第一个匹配元素内部区域宽度（包括补白、不包括边框）。</div> <div class="longdesc">此方法对可见和隐藏元素均有效。</div></div>
				<hr />
				<div class="desc"><div>Gets the inner width (excludes the border and includes the padding) for the first matched element.</div> <div class="longdesc">This method works for both visible and hidden elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Integer</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段落内部区域宽度。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;2nd Paragraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var p = $("p:first");<br/>
$("p:last").text( "innerWidth:" + p.innerWidth() );
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;innerWidth: 40&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">outerHeight(options)</div>
			<div class="content">
				<h1>outerHeight(options)</h1>
				<div class="desc"><div>获取第一个匹配元素外部高度（默认包括补白和边框）。</div> <div class="longdesc">此方法对可见和隐藏元素均有效。</div></div>
				<hr />
				<div class="desc"><div>Gets the outer height (includes the border and padding by default) for the first matched element.</div> <div class="longdesc">This method works for both visible and hidden elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Integer</p>
				<h2>参数</h2>
				<p class="indent"><strong>options</strong>(Boolean) : (false) 设置为 true 时，计算边距在内。</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段落外部高度。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;2nd Paragraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var p = $("p:first");<br/>
$("p:last").text( "outerHeight:" + p.outerHeight() + " , outerHeight(true):" + p.outerHeight(true) );
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;outerHeight: 35 , outerHeight(true):55&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">outerWidth(options)</div>
			<div class="content">
				<h1>outerHeight(options)</h1>
				<div class="desc"><div>获取第一个匹配元素外部宽度（默认包括补白和边框）。</div> <div class="longdesc">此方法对可见和隐藏元素均有效。</div></div>
				<hr />
				<div class="desc"><div>Gets the outer width (includes the border and padding by default) for the first matched element.</div> <div class="longdesc">This method works for both visible and hidden elements.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Integer</p>
				<h2>参数</h2>
				<p class="indent"><strong>options</strong>(Boolean) : (false) 设置为 true 时，计算边距在内。</p>
				<h2>示例</h2>
				<p class="indent">
					获取第一段落外部宽度。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;2nd Paragraph&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var p = $("p:first");<br/>
$("p:last").text( "outerWidth:" + p.outerWidth() + " , outerWidth(true):" + p.outerWidth(true) );
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p&gt;outerWidth: 65 , outerWidth(true):85&lt;/p&gt; 
				</div>
			</div>
		</div>
	</div>
	<!-- Events -->
	<div class="menuitem">事件</div>
	<div class="functionmenu">
		<div class="categoryitem">页面载入</div>
		<div class="category">
			<div class="functionitem">ready(fn)</div>
			<div class="content">
				<h1>ready(fn)</h1>
				<div class="desc"><div>当DOM载入就绪可以查询及操纵时绑定一个要执行的函数。</div> <div class="longdesc"><p>这是事件模块中最重要的一个函数，因为它可以极大地提高web应用程序的响应速度。</p><p>简单地说，这个方法纯粹是对向window.load事件注册事件的替代方法。通过使用这个方法，可以在DOM载入就绪能够读取并操纵时立即调用你所绑定的函数，而99.99%的JavaScript函数都需要在那一刻执行。</p><p>有一个参数－－对jQuery函数的引用－－会传递到这个ready事件处理函数中。可以给这个参数任意起一个名字，并因此可以不再担心命名冲突而放心地使用$别名。</p><p>请确保在 &lt;body&gt; 元素的onload事件中没有注册函数，否则不会触发$(document).ready()事件。</p><p>可以在同一个页面中无限次地使用$(document).ready()事件。其中注册的函数会按照（代码中的）先后顺序依次执行。</p></div></div><hr />
				<div class="desc"><div>Binds a function to be executed whenever the DOM is ready to be traversed and manipulated.</div> <div class="longdesc"><p>This is probably the most important function included in the event module, as it can greatly improve the response times of your web applications.</p><p>In a nutshell, this is a solid replacement for using window.onload, and attaching a function to that. By using this method, your bound function will be called the instant the DOM is ready to be read and manipulated, which is when what 99.99% of all JavaScript code needs to run.</p><p>There is one argument passed to the ready event handler: A reference to the jQuery function. You can name that argument whatever you like, and can therefore stick with the $ alias without risk of naming collisions.</p><p>Please ensure you have no code in your &lt;body&gt; onload event handler, otherwise $(document).ready() may not fire.</p><p>You can have as many $(document).ready events on your page as you like. The functions are then executed in the order they were added.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 要在DOM就绪时执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					在DOM加载完成时运行的代码，可以这样写：
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(document).ready(function(){<br/>
&nbsp; // 在这里写你的代码...<br/>
});
				</div>
				<hr />
				<p class="indent">
					使用 $(document).ready() 的简写，同时内部的 jQuery 代码依然使用 $ 作为别名，而不管全局的 $ 为何。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					jQuery(function($) {<br/>
&nbsp; // 你可以在这里继续使用$作为别名...<br/>
});
				</div>
			</div>
		</div>
		<div class="categoryitem">事件处理</div>
		<div class="category">
			<div class="functionitem">bind(type,data,fn)</div>
			<div class="content">
				<h1>bind(type,[data],fn)</h1>
				<div class="desc"><div>为每一个匹配元素的特定事件（像click）绑定一个事件处理器函数。</div> <div class="longdesc">这个事件处理函数会接收到一个事件对象，可以通过它来阻止（浏览器）默认的行为。如果既想取消默认的行为，又想阻止事件起泡，这个事件处理函数必须返回false。多数情况下，可以把事件处理器函数定义为匿名函数（见示例一）。在不可能定义匿名函数的情况下，可以传递一个可选的数据对象作为第二个参数（而事件处理器函数则作为第三个参数），见示例二。</div></div><hr />
				<div class="desc"><div>Binds a handler to a particular event (like click) for each matched element.</div> <div class="longdesc">The event handler is passed an event object that you can use to prevent default behaviour. To stop both default action and event bubbling, your handler has to return false. In most cases, you can define your event handlers as anonymous functions (see first example). In cases where that is not possible, you can pass additional data as the second parameter (and the handler function as the third), see second example.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>type </strong>(String) : 事件类型</p>
				<p class="indent"><strong>data </strong>(Object) : (可选) 作为event.data属性值传递给事件对象的额外数据对象</p>
				<p class="indent"><strong>fn </strong>(	Function) : 绑定到每个匹配元素的事件上面的处理函数</p>
				<h2>示例</h2>
				<p class="indent">
					当每个段落被点击的时候，弹出其文本。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").bind("click", function(){<br/>
&nbsp; alert( $(this).text() );<br/>
});
				</div>
				<hr />
				<p class="indent">
					你可以在事件处理之前传递一些附加的数据。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					function handler(event) {<br/>
&nbsp; alert(event.data.foo);<br/>
}<br/>
$("p").bind("click", {foo: "bar"}, handler) 
				</div>
				<hr />
				<p class="indent">
					通过返回false来取消默认的行为并阻止事件起泡。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form").bind("submit", function() { return false; }) 
				</div>
				<hr />
				<p class="indent">
					通过使用 preventDefault() 方法只取消默认的行为。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form").bind("submit", function(event){<br/>
&nbsp; event.preventDefault();<br/>
});
				</div>
				<hr />
				<p class="indent">
					通过使用 stopPropagation() 方法只阻止一个事件起泡。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form").bind("submit", function(event){<br/>
&nbsp; event.stopPropagation();<br/>
});
				</div>
			</div>
			<div class="functionitem">one(type,data,fn)</div>
			<div class="content">
				<h1>one(type,[data],fn)</h1>
				<div class="desc"><div>为每一个匹配元素的特定事件（像click）绑定一个<em>一次性</em>的事件处理函数。</div> <div class="longdesc"><p>在每个对象上，这个事件处理函数只会被执行一次。其他规则与bind()函数相同。这个事件处理函数会接收到一个事件对象，可以通过它来阻止（浏览器）默认的行为。如果既想取消默认的行为，又想阻止事件起泡，这个事件处理函数必须返回false。</p><p>多数情况下，可以把事件处理函数定义为匿名函数（见示例一）。在不可能定义匿名函数的情况下，可以传递一个可选的数据对象作为第二个参数（而事件处理函数则作为第三个参数），见示例二。</p></div></div><hr />
				<div class="desc"><div>Binds a handler to a particular event to be executed <em>once</em> for each matched element.</div> <div class="longdesc"><p>The handler is executed only once for each element. Otherwise, the same rules as described in 'bind()' apply. The event handler is passed an event object that you can use to prevent default behaviour. To stop both default action and event bubbling, your handler should return false.</p><p>In most cases, you can define your event handlers as anonymous functions (see first example). In cases where that is not possible, you can pass additional data as the second paramter (and the handler function as the third), see second example.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>type </strong>(String) : 事件类型</p>
				<p class="indent"><strong>data </strong>(Object) : (可选) 作为event.data属性值传递给事件对象的额外数据对象</p>
				<p class="indent"><strong>fn </strong>(Function) : 绑定到每个匹配元素的事件上面的处理函数</p>
				<h2>示例</h2>
				<p class="indent">
					当所有段落被第一次点击的时候，显示所有其文本。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").one("click", function(){<br/>
&nbsp; alert( $(this).text() );<br/>
});
				</div>
			</div>
			<div class="functionitem">trigger(type,data)</div>
			<div class="content">
				<h1>trigger(type,[data])</h1>
				<div class="desc"><div>在每一个匹配的元素上触发某类事件。</div> <div class="longdesc"><p>这个函数也会导致浏览器同名的默认行为的执行。比如，如果用trigger()触发一个'submit'，则同样会导致浏览器提交表单。如果要阻止这种默认行为，应返回false。</p><p>你也可以触发由bind()注册的自定义事件而不限于浏览器默认事件。</p><p>事件处理函数会收到一个修复的(规范化的)事件对象，但这个对象没有特定浏览器才有的属性，比如keyCode。</p><p>jQuery也支持 <a href="http://docs.jquery.com/Namespaced_Events">命名空间事件</a>。这允许你触发或者解除绑定一组特定的事件处理函数，而无需一一个指定。你可以在事件类型后面加上感叹号 ! 来只触发那些没有命名空间的事件处理函数。</p><p><strong>jQuery 1.3中新增：</strong></p><p>所有触发的事件现在会冒泡到DOM树上了。举例来说，如果你在一个段落p上触发一个事件，他首先会在这个元素上触发，其次到父元素，在到父元素的父元素，直到触发到document对象。这个事件对象有一个 .target 属性指向最开始触发这个事件的元素。你可以用 stopPropagation() 来阻止事件冒泡，或者在事件处理函数中返回false即可。</p><p>事件对象构造器现在已经公开，并且你可以自行创建一个事件对象。这个事件对象可以直接传递给trigger所触发的事件处理函数。事件对象的完整属性列表可以在 <a href="http://docs.jquery.com/Events/jQuery.Event">jQuery.Event</a> 的文档里找到。</p><p>你可以有三种方式指定事件类型：</p><p>* 你可以传递字符串型的事件名称(type参数)。</p><p>* 你可以使用jQuery.Event对象。可以将数据放进这个对象，并且这个对象可以被触发的事件处理函数获取到。</p><p>* 最后，你可以传递一个带有数据的字面量对象。他将被复制到真正的jQuery.Event对象上去。 注意在这种情况下你<strong>必须</strong>指定一个 <em>type</em> 属性。</p></div></div><hr />
				<div class="desc"><div>Trigger a type of event on every matched element.</div> <div class="longdesc"><p>This will also cause the default action of the browser with the same name (if one exists) to be executed. For example, passing 'submit' to the trigger() function will also cause the browser to submit the form. This default action can be prevented by returning false from one of the functions bound to the event.</p><p>You can also trigger custom events registered with bind.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>type </strong>(String,Event,Object) : 一个事件对象或者要触发的事件类型</p>
				<p class="indent"><strong>data </strong>(Array) : (可选)传递给事件处理函数的附加参数</p>
				<h2>示例</h2>
				<p class="indent">
					提交第一个表单，但不用submit()
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form:first").trigger("submit") 
				</div>
				<hr />
				<p class="indent">
					给一个事件传递参数
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").click( function (event, a, b) {<br/>
&nbsp; // 一个普通的点击事件时，a和b是undefined类型<br/>
&nbsp; // 如果用下面的语句触发，那么a指向"foo",而b指向"bar"<br/>
} ).trigger("click", ["foo", "bar"]); 
				</div>
				<hr />
				<p class="indent">
					下面的代码可以显示一个"Hello World"
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").bind("myEvent", function (event, message1, message2) {<br/>
&nbsp; alert(message1 + ' ' + message2);<br/>
});<br/>
$("p").trigger("myEvent", ["Hello","World!"]); 
				</div>
			</div>
			<div class="functionitem">triggerHandler(type,data)</div>
			<div class="content">
				<h1>triggerHandler(type,[data])</h1>
				<div class="desc">这个特别的方法将会触发指定的事件类型上所有绑定的处理函数。但<strong>不会执行</strong>浏览器默认动作，也不会产生事件冒泡。<div class="longdesc">这个方法的行为表现与trigger类似，但有以下三个主要区别： <p>* 第一，他不会触发浏览器默认事件。</p><p>* 第二，只触发jQuery对象集合中第一个元素的事件处理函数。</p>* 第三，这个方法的返回的是事件处理函数的返回值，而不是据有可链性的jQuery对象。此外，如果最开始的jQuery对象集合为空，则这个方法返回 undefined 。<p></p></div></div>
				<hr />
				<div class="desc">This particular method triggers all bound event handlers on an element (for a specific event type) WITHOUT executing the browsers default actions.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>type </strong>(String) : 要触发的事件类型</p>
				<p class="indent"><strong>data </strong>(Array) : (可选)传递给事件处理函数的附加参数</p>
				<h2>示例</h2>
				<p class="indent">
					如果你对一个focus事件执行了 .triggerHandler() ，浏览器默认动作将不会被触发，只会触发你绑定的动作。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;button id="old"&gt;.trigger("focus")&lt;/button&gt;<br/>
&lt;button id="new"&gt;.triggerHandler("focus")&lt;/button&gt;&lt;br/&gt;&lt;br/&gt;<br/>
&lt;input type="text" value="To Be Focused"/&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#old").click(function(){<br/>
&nbsp; $("input").trigger("focus");<br/>
});<br/>
$("#new").click(function(){<br/>
&nbsp; $("input").triggerHandler("focus");<br/>
});<br/>
$("input").focus(function(){
&nbsp; $("&lt;span&gt;Focused!&lt;/span&gt;").appendTo("body").fadeOut(1000);
});
				</div>
			</div>
			<div class="functionitem">unbind(type,data)</div>
			<div class="content">
				<h1>unbind([type],[data])</h1>
				<div class="desc"><div>bind()的反向操作，从每一个匹配的元素中删除绑定的事件。</div> <div class="longdesc"><p>如果没有参数，则删除所有绑定的事件。</p><p>你可以将你用bind()注册的自定义事件取消绑定。</p><p>I如果提供了事件类型作为参数，则只删除该类型的绑定事件。</p><p>如果把在绑定时传递的处理函数作为第二个参数，则只有这个特定的事件处理函数会被删除。</p></div></div><hr />
				<div class="desc"><div>This does the opposite of bind, it removes bound events from each of the matched elements.</div> <div class="longdesc"><p>Without any arguments, all bound events are removed.</p><p>You can also unbind custom events registered with bind.</p><p>If the type is provided, all bound events of that type are removed.</p><p>If the function that was passed to bind is provided as the second argument, only that specific event handler is removed.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>type </strong>(String) : (可选) 事件类型</p>
				<p class="indent"><strong>data </strong>(Function) : (可选) 要从每个匹配元素的事件中反绑定的事件处理函数</p>
				<h2>示例</h2>
				<p class="indent">
					把所有段落的所有事件取消绑定
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").unbind() 
				</div>
				<hr />
				<p class="indent">
					将段落的click事件取消绑定
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").unbind( "click" ) 
				</div>
				<hr />
				<p class="indent">
					删除特定函数的绑定，将函数作为第二个参数传入
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var foo = function () {<br/>
&nbsp; // 处理某个事件的代码<br/>
};<br/>
<br/>
$("p").bind("click", foo); // ... 当点击段落的时候会触发 foo <br/>
<br/>
$("p").unbind("click", foo); // ... 再也不会被触发 foo <br/>
				</div>
			</div>
		</div>
		<div class="categoryitem">事件委派</div>
		<div class="category">
			<div class="functionitem">live( type, fn )</div>
			<div class="content">
				<h1>live( type, fn )</h1>
				<div class="desc"><div>jQuery 1.3中新增的方法。给所有当前以及将来会匹配的元素绑定一个事件处理函数（比如click事件）。也能绑定自定义事件。</div> <div class="longdesc"><p>目前支持 click, dblclick, mousedown, mouseup, mousemove, mouseover, mouseout, keydown, keypress, keyup。 <br />还不支持 blur, focus, mouseenter, mouseleave, change, submit <br />与bind()不同的是，live()一次只能绑定一个事件。</p><p>这个方法跟传统的bind很像，区别在于用live来绑定事件会给所有当前以及将来在页面上的元素绑定事件(使用委派的方式)。比如说，如果你给页面上所有的li用live绑定了click事件。那么当在以后增加一个li到这个页面时，对于这个新增加的li，其click事件依然可用。而无需重新给这种新增加的元素绑定事件。</p><p>.live()与流行的liveQuery插件很像，但有以下几个主要区别：</p><ul>
					<li>.live 目前只支持所有事件的子集，支持列表参考上面的说明。</li>
					<li>.live 不支持liveQuery提供的“无事件”样式的回调函数。.live只能绑定事件处理函数。</li>
					<li>.live 没有"setup"和"cleanup"的过程。因为所有的事件是委派而不是直接绑定在元素上的。</li>
				</ul>
				<p>要移除用live绑定的事件，请用die方法</p>
				</div></div><hr />
				<div class="desc"><div>Added in jQuery 1.3: Binds a handler to an event (like click) for all current - and future - matched element. Can also bind custom events.</div> <div class="longdesc">Possible event values: click, dblclick, mousedown, mouseup, mousemove, mouseover, mouseout, keydown, keypress, keyup 
Currently not supported: blur, focus, mouseenter, mouseleave, change, submit
This method works and behaves very similarly to jQuery's bind method but with one important distinction: When you bind a "live" event it will bind to all current and future elements on the page (using event delegation). For example if you bound a live click to all "li" elements on the page then added another li at a later time - that click event would continue to work for the new element (this is not the case with bind which must be re-bound on all new elements).

.live() behaves similarly to the popular liveQuery plugin but with a few major differences:

.live (currently) supports a subset of all events. Note the full list of supported/not-supported events above.
.live doesn't support the no-event style callback that liveQuery provides. Only event handlers can be bound with .live.
.live doesn't have a "setup" or "cleanup" step, since all events are delegated rather than bound directly to an element.
To remove a live event you should use the die method.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>type </strong>(String) : 一个或多个用空格分隔的事件名</p>
				<p class="indent"><strong>fn </strong>(Function) : 欲绑定的事件处理函数</p>
				<h2>示例</h2>
				<p class="indent">
					点击生成的p依然据有同样的功能。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
&lt;p&gt;Click me!&lt;/p&gt;<br />
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
$("p").live("click", function(){<br />
&nbsp;&nbsp;&nbsp;&nbsp;$(this).after("&lt;p&gt;Another paragraph!&lt;/p&gt;");<br />
});<br />
				</div>
			</div>
			<div class="functionitem">die( [type], [fn] )</div>
			<div class="content">
				<h1>toggle(fn,fn)</h1>
				<div class="desc"><div>jQuery 1.3新增。此方法与live正好完全相反。</div> <div class="longdesc"><p>如果不带参数，则所有绑定的live事件都会被移除。</p><p>你可以解除用live注册的自定义事件。</p><p>如果提供了type参数，那么会移除对应的live事件。</p><p>如果也指定了第二个参数function,则只移出指定的事件处理函数。</p></div></div><hr />
				<div class="desc"><div>Added in jQuery 1.3: This does the opposite of live, it removes a bound live event.</div> <div class="longdesc"><p>Without any arguments, all bound live events are removed.

You can also unbind custom events registered with live.

If the type is provided, all bound live events of that type are removed.

If the function that was passed to live is provided as the second argument, only that specific event handler is removed.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>type  </strong>(String) : 可选，要解除绑定的live事件</p>
				<p class="indent"><strong>fn  </strong>(Function) : 可选，要解除绑定的特定处理函数</p>
				<h2>示例</h2>
				<p class="indent">
					给按钮解除click事件
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
  function aClick() {
      $("div").show().fadeOut("slow");
  }
  $("#unbind").click(function () {
      $("#theone").die("click", aClick)
  });
				</div>
			</div>
		</div>
		<div class="categoryitem">事件切换</div>
		<div class="category">
			<div class="functionitem">hover(over,out)</div>
			<div class="content">
				<h1>hover(over,out)</h1>
				<div class="desc"><div>一个模仿悬停事件（鼠标移动到一个对象上面及移出这个对象）的方法。这是一个自定义的方法，它为频繁使用的任务提供了一种“保持在其中”的状态。</div> <div class="longdesc">当鼠标移动到一个匹配的元素上面时，会触发指定的第一个函数。当鼠标移出这个元素时，会触发指定的第二个函数。而且，会伴随着对鼠标是否仍然处在特定元素中的检测（例如，处在div中的图像），如果是，则会继续保持“悬停”状态，而不触发移出事件（修正了使用mouseout事件的一个常见错误）。</div></div><hr />
				<div class="desc"><div>Simulates hovering (moving the mouse on, and off, an object). This is a custom method which provides an 'in' to a frequent task.</div> <div class="longdesc">Whenever the mouse cursor is moved over a matched element, the first specified function is fired. Whenever the mouse moves off of the element, the second specified function fires. Additionally, checks are in place to see if the mouse is still within the specified element itself (for example, an image inside of a div), and if it is, it will continue to 'hover', and not move out (a common error in using a mouseout event handler).</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>over </strong>(Function) : 鼠标移到元素上要触发的函数</p>
				<p class="indent"><strong>out </strong>(Function) : 鼠标移出元素要触发的函数</p>
				<h2>示例</h2>
				<p class="indent">
					鼠标悬停的表格加上特定的类
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("td").hover(<br/>
&nbsp; function () {<br/>
&nbsp;&nbsp;&nbsp; $(this).addClass("hover");<br/>
&nbsp; },<br/>
&nbsp; function () {<br/>
&nbsp;&nbsp;&nbsp; $(this).removeClass("hover");<br/>
&nbsp; }<br/>
); 
				</div>
			</div>
			<div class="functionitem">toggle(fn,fn2[,fn3,fn4,...])</div>
			<div class="content">
				<h1>toggle(fn,fn)</h1>
				<div class="desc"><div>每次点击后依次调用函数。</div> <div class="longdesc"><p>如果点击了一个匹配的元素，则触发指定的第一个函数，当再次点击同一元素时，则触发指定的第二个函数，如果有更多函数，则再次触发，直到最后一个。随后的每次点击都重复对这几个函数的轮番调用。</p><p>可以使用unbind("click")来删除。</p></div></div><hr />
				<div class="desc"><div>Toggle among two or more function calls every other click.</div> <div class="longdesc"><p>Whenever a matched element is clicked, the first specified function is fired, when clicked again, the second is fired, and so on. All subsequent clicks continue to rotate through the functions.</p><p>Use unbind("click") to remove.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 第一数次点击时要执行的函数。</p>
				<p class="indent"><strong>fn2 </strong>(Function) : 第二数次点击时要执行的函数。</p>
				<p class="indent"><strong>fn3,fn4,... </strong>(Function) : 更多次点击时要执行的函数。</p>
				<h2>示例</h2>
				<p class="indent">
					对表格的切换一个类
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("td").toggle(<br/>
&nbsp; function () {<br/>
&nbsp;&nbsp;&nbsp; $(this).addClass("selected");<br/>
&nbsp; },<br/>
&nbsp; function () {<br/>
&nbsp;&nbsp;&nbsp; $(this).removeClass("selected");<br/>
&nbsp; }<br/>
); 
				</div>
				<p class="indent">
					对列表的切换样式
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
&nbsp;&nbsp;&lt;ul&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;Go&nbsp;to&nbsp;the&nbsp;store&lt;/li&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;Pick&nbsp;up&nbsp;dinner&lt;/li&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;Debug&nbsp;crash&lt;/li&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;li&gt;Take&nbsp;a&nbsp;jog&lt;/li&gt;<br />
&nbsp;&nbsp;&lt;/ul&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
&nbsp;&nbsp;&nbsp;&nbsp;$("li").toggle(<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;function&nbsp;()&nbsp;{<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(this).css({"list-style-type":"disc",&nbsp;"color":"blue"});<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;function&nbsp;()&nbsp;{<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(this).css({"list-style-type":"disc",&nbsp;"color":"red"});<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;function&nbsp;()&nbsp;{<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(this).css({"list-style-type":"",&nbsp;"color":""});<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br />
&nbsp;&nbsp;&nbsp;&nbsp;);<br />
				</div>
			</div>
		</div>
		<div class="categoryitem">事件</div>
		<div class="category">
			<div class="functionitem">blur()</div>
			<div class="content">
				<h1>blur()</h1>
				<div style="" id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的blur事件。</div> <div class="longdesc">这个函数会调用执行绑定到blur事件的所有函数，包括浏览器的默认行为。可以通过返回false来防止触发浏览器的默认行为。blur事件会在元素失去焦点的时候触发，既可以是鼠标行为，也可以是按tab键离开的</div></div> </div><hr />
				<div style="" id="overview" class="tabs-container">  <div class="desc"><div>Triggers the blur event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to that blur event to be executed, and calls the browser's default blur action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the blur event. The blur event usually fires when an element loses focus either via the pointing device or by tabbing navigation</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					触发所有段落的blur事件
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").blur(); 
				</div>
			</div>
			<div class="functionitem">blur(fn)</div>
			<div class="content">
				<h1>blur(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的blur事件中绑定一个处理函数。</div> <div class="longdesc">blur事件会在元素失去焦点的时候触发，既可以是鼠标行为，也可以是按tab键离开的</div></div><hr />
				<div class="desc"><div>Bind a function to the blur event of each matched element.</div> <div class="longdesc">The blur event fires when an element loses focus either via the pointing device or by tabbing navigation.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的blur事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					任何段落失去焦点时弹出一个 "Hello World!"在每一个匹配元素的blur事件中绑定的处理函数。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").blur( function () { alert("Hello World!"); } ); 
				</div>
			</div>
			<div class="functionitem">change()</div>
			<div class="content">
				<h1>change()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>触发每个匹配元素的change事件</div> <div class="longdesc">这个函数会调用执行绑定到change事件的所有函数，包括浏览器的默认行为。可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。change事件会在元素失去焦点的时候触发，也会当其值在获得焦点后改变时触发。</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Triggers the change event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to that change event to be executed, and calls the browser's default change action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the change event. The change event usually fires when a control loses the input focus and its value has been modified since gaining focus.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
			</div>
			<div class="functionitem">change(fn)</div>
			<div class="content">
				<h1>change(fn)</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>在每一个匹配元素的change事件中绑定一个处理函数。</div> <div class="longdesc">change事件会在元素失去焦点的时候触发，也会当其值在获得焦点后改变时触发。</div></div> </div><hr />
				<div class="desc"><div>Binds a function to the change event of each matched element.</div> <div class="longdesc">The change event fires when a control loses the input focus and its value has been modified since gaining focus.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的change事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					给所有的文本框增加输入验证
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[type='text']").change( function() {<br/>
&nbsp; // 这里可以写些验证代码<br/>
});
				</div>
			</div>
			<div class="functionitem">click()</div>
			<div class="content">
				<h1>click()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的click事件。</div> <div class="longdesc">这个函数会调用执行绑定到click事件的所有函数。</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Triggers the click event of each matched element.</div> <div class="longdesc">Causes all of the functions that have been bound to that click event to be executed.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					触发页面内所有段落的点击事件
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").click(); 
				</div>
			</div>
			<div class="functionitem">click(fn)</div>
			<div class="content">
				<h1>click(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的click事件中绑定一个处理函数。</div> <div class="longdesc">点击事件会在你的指针设备的按钮在元素上单击时触发。单击的定义是在屏幕的同一点触发了mousedown和mouseup.几个事件触发的顺序是：				<p>mousedown</p>
				<p>mouseup</p>
				<p>click</p></div></div><hr />
				<div class="desc"><div>Binds a function to the click event of each matched element.</div> <div class="longdesc">The click event fires when the pointing device button is clicked over an element. A click is defined as a mousedown and mouseup over the same screen location. The sequence of these events is:
				<p>mousedown</p>
				<p>mouseup</p>
				<p>click</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 绑定到click事件的函数</p>
				<h2>示例</h2>
				<p class="indent">
					将页面内所有段落点击后隐藏。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").click( function () { $(this).hide(); });
				</div>
			</div>
			<div class="functionitem">dblclick()</div>
			<div class="content">
				<h1>dblclick()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的dbclick事件。</div> <div class="longdesc">这个函数会调用执行绑定到dblclick事件的所有函数，包括浏览器的默认行为。可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。dblclick事件会在元素的同一点双击时触发。</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Triggers the dblclick event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to that dblclick event to be executed, and calls the browser's default dblclick action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the dblclick event. The dblclick event usually fires when the pointing device button is double clicked over an element.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
			</div>
			<div class="functionitem">dblclick(fn)</div>
			<div class="content">
				<h1>dblclick(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的dblclick事件中绑定一个处理函数。</div> <div class="longdesc">的那个在某个元素上双击的时候就会触发dblclick事件</div></div><hr />
				<div class="desc"><div>Binds a function to the dblclick event of each matched element.</div> <div class="longdesc">The dblclick event fires when the pointing device button is double clicked over an element</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的dblclick事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					给页面上每个段落的双击事件绑上 "Hello World!" 警告框
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").dblclick( function () { alert("Hello World!"); });
				</div>
			</div>
			<div class="functionitem">error()</div>
			<div class="content">
				<h1>error()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>这个函数会调用执行绑定到error事件的所有函数，包括浏览器的默认行为。可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Triggers the error event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to that error event to be executed, and calls the browser's default error action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the error event. The error event usually fires when an element loses focus either via the pointing device or by tabbing navigation.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
			</div>
			<div class="functionitem">error(fn)</div>
			<div class="content">
				<h1>error(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的error事件中绑定一个处理函数。</div> <div class="longdesc"><p>对于error事件，没有一个公众的标准。在大多数浏览器中，当页面的JavaScript发生错误时，window对象会触发error事件;当图像的src属性无效时，比如文件不存在或者图像数据错误时，也会触发图像对象的error事件。</p><p>如果异常是由window对象抛出，事件处理函数将会被传入三个参数：</p><p>1. 描述事件的信息 ("varName is not defined", "missing operator in expression", 等等.),</p><p>2. 包含错误的文档的完整URL</p><p>3. 异常发生的行数</p> <p>如果事件处理函数返回true，则表示事件已经被处理，浏览器将认为没有异常。</p><p>更多相关信息: </p><p><a href="http://msdn2.microsoft.com/en-us/library/ms536930.aspx" >msdn - onerror Event</a></p><p><a href="http://developer.mozilla.org/en/docs/DOM:window.onerror" >Gecko DOM Reference - onerror Event</a></p><p><a href="http://developer.mozilla.org/en/docs/DOM:event">Gecko DOM Reference - Event object</a></p><p><a href="http://en.wikipedia.org/wiki/DOM_Events">Wikipedia: DOM Events</a></p></div></div><hr />
				<div class="desc"><div>Binds a function to the error event of each matched element.</div> <div class="longdesc"><p>There is no public standard for the error event. In most browsers, the window object's error event is triggered when a JavaScript error occurs on the page. An image object's error event is triggered when it is set an invalid src attribute - either a non-existent file, or one with corrupt image data.</p><p>If the event is thrown by the window object, the event handler will be passed three parameters: </p><p>1. A message describing the event ("varName is not defined", "missing operator in expression", etc.),</p><p>2. the full URL of the document containing the error, and</p><p>3. the line number on which the error occured.</p> <p>If the event handler returns true, it signifies that the event was handled, and the browser raises no errors.</p><p>For more information, see: </p><p><a href="http://msdn2.microsoft.com/en-us/library/ms536930.aspx" >msdn - onerror Event</a></p><p><a href="http://developer.mozilla.org/en/docs/DOM:window.onerror" >Gecko DOM Reference - onerror Event</a></p><p><a href="http://developer.mozilla.org/en/docs/DOM:event">Gecko DOM Reference - Event object</a></p><p><a href="http://en.wikipedia.org/wiki/DOM_Events">Wikipedia: DOM Events</a></p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) :在每一个匹配元素的error事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					在服务器端记录JavaScript错误日志: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(window).error(function(msg, url, line){<br/>
&nbsp; jQuery.post("js_error_log.php", { msg: msg, url: url, line: line });<br/>
});
				</div>
				<hr />
				<p class="indent">
					隐藏JavaScript错误: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(window).error(function(){<br/>
&nbsp; return true;<br/>
});
				</div>
				<hr />
				<p class="indent">
					给你IE的用户隐藏无效的图像: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("img").error(function(){<br/>
&nbsp; $(this).hide();<br/>
});
				</div>
			</div>
			<div class="functionitem">focus()</div>
			<div class="content">
				<h1>focus()</h1>
				<div style="" id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的focus事件。</div> <div class="longdesc">这将触发所有绑定的focus函数，注意，某些对象不支持focus方法。</div></div> </div><hr />
				<div style="" id="overview" class="tabs-container">  <div class="desc"><div>Triggers the focus event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to the focus event to be executed. Note that this does not execute the focus method of the underlying elements.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					当页面加载后将 id 为 'login' 的元素设置焦点: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(document).ready(function(){<br/>
&nbsp; $("#login").focus();<br/>
});
				</div>
			</div>
			<div class="functionitem">focus(fn)</div>
			<div class="content">
				<h1>focus(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的focus事件中绑定一个处理函数。</div> <div class="longdesc">focus事件可以通过鼠标点击或者键盘上的TAB导航触发</div></div><hr />
				<div class="desc"><div>Binds a function to the focus event of each matched element.</div> <div class="longdesc">The focus event fires when an element receives focus either via the pointing device or by tab navigation.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的focus事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					使人无法使用文本框: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input[type=text]").focus(function(){<br/>
&nbsp; this.blur();<br/>
});
				</div>
			</div>
			<div class="functionitem">keydown()</div>
			<div class="content">
				<h1>keydown()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的keydown事件</div> <div class="longdesc">这个函数会调用执行绑定到keydown事件的所有函数，包括浏览器的默认行为。可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。keydown事件会在键盘按下时触发。</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Triggers the keydown event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to the keydown event to be executed, and calls the browser's default keydown action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the keydown event. The keydown event usually fires when a key on the keyboard is pressed.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
			</div>
			<div class="functionitem">keydown(fn)</div>
			<div class="content">
				<h1>keydown(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的keydown事件中绑定一个处理函数。</div> <div class="longdesc">keydown事件会在键盘按下时触发。</div></div><hr />
				<div class="desc"><div>Bind a function to the keydown event of each matched element.</div> <div class="longdesc">The keydown event fires when a key on the keyboard is pressed.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的keydown事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					在页面内对键盘按键做出回应，可以使用如下代码: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(window).keydown(function(event){<br/>
&nbsp; switch(event.keyCode) {<br/>
&nbsp;&nbsp;&nbsp; // ...<br/>
&nbsp;&nbsp;&nbsp; // 不同的按键可以做不同的事情<br/>
&nbsp;&nbsp;&nbsp; // 不同的浏览器的keycode不同<br/>
&nbsp;&nbsp;&nbsp; // 更多详细信息: <br/>&nbsp;&nbsp;&nbsp; http://unixpapa.com/js/key.html<br/>
&nbsp;&nbsp;&nbsp; // ...<br/>
&nbsp; }<br/>
});
				</div>
			</div>
			<div class="functionitem">keypress()</div>
			<div class="content">
				<h1>keypress()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的keypress事件</div> <div class="longdesc">T这个函数会调用执行绑定到keydown事件的所有函数，包括浏览器的默认行为。可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。keydown事件会在键盘按下时触发</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Triggers the keypress event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to the keypress event to be executed, and calls the browser's default keypress action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the keypress event. The keypress event usually fires when a key on the keyboard is pressed.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
			</div>
			<div class="functionitem">keypress(fn)</div>
			<div class="content">
				<h1>keypress(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的keypress事件中绑定一个处理函数。</div> <div class="longdesc">keypress事件会在敲击按键时触发。 敲击按键的定义为按下并抬起同一个按键。这几个事件发生的顺序是:<p>keydown</p><p>keypress</p><p>keyup</p></div></div><hr />
				<div class="desc"><div>Binds a function to the keypress event of each matched element.</div> <div class="longdesc">The keypress event fires when a key on the keyboard is "clicked". A keypress is defined as a keydown and keyup on the same key. The sequence of these events is: <p>keydown</p><p>keyup</p><p>keypress</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的keypress事件中绑定的处理函数。</p>
			</div>
			<div class="functionitem">keyup()</div>
			<div class="content">
				<h1>keyup()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的keyup事件</div> <div class="longdesc">这个函数会调用执行绑定到keyup事件的所有函数，包括浏览器的默认行为。可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。keyup事件会在按键释放时触发。</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Triggers the keyup event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to the keyup event to be executed, and calls the browser's default keyup action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the keyup event. The keyup event usually fires when a key on the keyboard is released.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
			</div>
			<div class="functionitem">keyup(fn)</div>
			<div class="content">
				<h1>keyup(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的keyup事件中绑定一个处理函数。</div> <div class="longdesc">keyup 事件会在键盘按下时触发。</div></div><hr />
				<div class="desc"><div>Bind a function to the keyup event of each matched element.</div> <div class="longdesc">The keyup event fires when a key on the keyboard is released.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) :在每一个匹配元素的keyup事件中绑定的处理函数。</p>
			</div>
			<div class="functionitem">load(fn)</div>
			<div class="content">
				<h1>load(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的load事件中绑定一个处理函数。</div> <div class="longdesc"><p>如果绑定给window对象，则会在所有内容加载后触发，包括窗口，框架，对象和图像。如果绑定在元素上，则当元素的内容加载完毕后触发。</p><p><strong>注意:</strong>只有当在这个元素完全加载完之前绑定load的处理函数,才会在他加载完后触发。如果之后再绑定就永远不会触发了。所以<strong>不要</strong>在$(document).ready()里绑定load事件，因为jQuery会在所有DOM加载完成后再绑定load事件。</p></div></div><hr />
				<div class="desc"><div>Binds a function to the load event of each matched element.</div> <div class="longdesc"><p>When bound to the window element, the event fires when the user agent finishes loading all content within a document, including window, frames, objects and images. For elements, it fires when the target element and all of its content has finished loading.</p><p>Note: load will work only if you set it before the element has completely loaded, if you set it after that nothing will happen. This doesn't happen in $(document).ready(), which jQuery handles it to work as expected, also when setting it after the DOM has loaded.</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的load事件中绑定的处理函数。</p>
			</div>
			<div class="functionitem">mousedown(fn)</div>
			<div class="content">
				<h1>mousedown(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的mousedown事件中绑定一个处理函数。</div> <div class="longdesc">mousedown事件在鼠标在元素上点击后会触发</div></div><hr />
				<div class="desc"><div>Binds a function to the mousedown event of each matched element.</div> <div class="longdesc">The mousedown event fires when the pointing device button is pressed over an element.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的mousedown事件中绑定的处理函数。</p>
			</div>
			<div class="functionitem">mousemove(fn)</div>
			<div class="content">
				<h1>mousemove(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的mousemove事件中绑定一个处理函数。</div> <div class="longdesc">mousemove 事件通过鼠标在元素上移动来触发。事件处理函数会被传递一个变量——事件对象，其.clientX 和 .clientY 属性代表鼠标的坐标</div></div><hr />
				<div class="desc"><div>Bind a function to the mousemove event of each matched element.</div> <div class="longdesc">The mousemove event fires when the pointing device is moved while it is over an element. The event handler is passed one variable - the event object. Its .clientX and .clientY properties represent the coordinates of the mouse.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的mousemove事件中绑定的处理函数。</p>
			</div>
			<div class="functionitem">mouseout(fn)</div>
			<div class="content">
				<h1>mouseout(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的mouseout事件中绑定一个处理函数。</div> <div class="longdesc">mouseout事件在鼠标从元素上离开后会触发</div></div><hr />
				<div class="desc"><div>Bind a function to the mouseout event of each matched element.</div> <div class="longdesc">The mouseout event fires when the pointing device is moved away from an element.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的mouseout事件中绑定的处理函数。</p>
			</div>
			<div class="functionitem">mouseover(fn)</div>
			<div class="content">
				<h1>mouseover(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的mouseover事件中绑定一个处理函数。</div> <div class="longdesc">mouseover事件会在鼠标移入对象时触发</div></div><hr />
				<div class="desc"><div>Bind a function to the mouseover event of each matched element.</div> <div class="longdesc">The mouseout event fires when the pointing device is moved onto an element.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的mouseover事件中绑定的处理函数。</p>
			</div>
			<div class="functionitem">mouseup(fn)</div>
			<div class="content">
				<h1>mouseup(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的mouseup事件中绑定一个处理函数。</div> <div class="longdesc">mouseup事件会在鼠标点击对象释放时</div></div><hr />
				<div class="desc"><div>Bind a function to the mouseup event of each matched element.</div> <div class="longdesc">The mouseup event fires when the pointing device button is released over an element.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的mouseup事件中绑定的处理函数。</p>
			</div>
			<div class="functionitem">resize(fn)</div>
			<div class="content">
				<h1>resize(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的resize事件中绑定一个处理函数。</div> <div class="longdesc">当文档窗口改变大小时触发</div></div><hr />
				<div class="desc"><div>Bind a function to the resize event of each matched element.</div> <div class="longdesc">The resize event fires when a document view is resized</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) :在每一个匹配元素的resize事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					让人每次改变页面窗口的大小时很郁闷的方法: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(window).resize(function(){<br/>
&nbsp; alert("Stop it!");<br/>
});
				</div>
			</div>
			<div class="functionitem">scroll(fn)</div>
			<div class="content">
				<h1>scroll(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的scroll事件中绑定一个处理函数。</div> <div class="longdesc">当滚动条发生变化时触发</div></div><hr />
				<div class="desc"><div>Bind a function to the scroll event of each matched element.</div> <div class="longdesc">The scroll event fires when a document view is scrolled.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的resize事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					当页面滚动条变化时，执行的函数: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(window).scroll( function() { /* ...do something... */ } ); 
				</div>
			</div>
			<div class="functionitem">select()</div>
			<div class="content">
				<h1>select()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的select事件</div> <div class="longdesc">这个函数会调用执行绑定到select事件的所有函数，包括浏览器的默认行为。可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Trigger the select event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to that select event to be executed, and calls the browser's default select action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the select event.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					触发所有input元素的select事件: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("input").select(); 
				</div>
			</div>
			<div class="functionitem">select(fn)</div>
			<div class="content">
				<h1>select(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的select事件中绑定一个处理函数。</div> <div class="longdesc">当用户在文本框(包括input和textarea)中选中某段文本时会触发select事件。</div></div><hr />
				<div class="desc"><div>Bind a function to the select event of each matched element.</div> <div class="longdesc">The select event fires when a user selects some text in a text field, including input and textarea.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的select事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					当文本框中文本被选中时执行的函数: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(":text").select( function () { /* ...do something... */ } ); 
				</div>
			</div>
			<div class="functionitem">submit()</div>
			<div class="content">
				<h1>submit()</h1>
				<div id="overview" class="tabs-container">  <div class="desc"><div>触发每一个匹配元素的submit事件。</div> <div class="longdesc">这个函数会调用执行绑定到submit事件的所有函数，包括浏览器的默认行为。可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。</div></div> </div><hr />
				<div id="overview" class="tabs-container">  <div class="desc"><div>Trigger the submit event of each matched element.</div> <div class="longdesc">This causes all of the functions that have been bound to that submit event to be executed, and calls the browser's default submit action on the matching element(s). This default action can be prevented by returning false from one of the functions bound to the submit event.</div></div> </div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					提交本页的第一个表单: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form:first").submit(); 
				</div>
			</div>
			<div class="functionitem">submit(fn)</div>
			<div class="content">
				<h1>submit(fn)</h1>
				<div class="desc"><div>在每一个匹配元素的submit事件中绑定一个处理函数。</div> <div class="longdesc">submit事件将会在表单提交时触发</div></div><hr />
				<div class="desc"><div>Bind a function to the submit event of each matched element.</div> <div class="longdesc">The select event fires when a form is submitted</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) :在每一个匹配元素的submit事件中绑定的处理函数</p>
				<h2>示例</h2>
				<p class="indent">
					如果你要阻止表单提交: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("form").submit( function () {<br/>
&nbsp; return false;<br/>
} ); 
				</div>
			</div>
			<div class="functionitem">unload(fn)</div>
			<div class="content">
				<h1>unload(fn)</h1>
				<div class="desc">在每一个匹配元素的unload事件中绑定一个处理函数。</div><hr />
				<div class="desc">Binds a function to the unload event of each matched element.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>fn </strong>(Function) : 在每一个匹配元素的unload事件中绑定的处理函数。</p>
				<h2>示例</h2>
				<p class="indent">
					页面卸载的时候弹出一个警告框: 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$(window).unload( function () { alert("Bye now!"); } ); 
				</div>
			</div>
		</div>
	</div>
	<!-- Effects -->
	<div class="menuitem">效果</div>
	<div class="functionmenu">
		<div class="categoryitem">基本</div>
		<div class="category">
			<div class="functionitem">show()</div>
			<div class="content">
				<h1>show()</h1>
				<div class="desc"><div>显示隐藏的匹配元素。</div> <div class="longdesc">这个就是 'show( speed, [callback] )' 无动画的版本。如果选择的元素是可见的，这个方法将不会改变任何东西。无论这个元素是通过hide()方法隐藏的还是在CSS里设置了display:none;，这个方法都将有效。</div></div><hr />
				<div class="desc"><div>Displays each of the set of matched elements if they are hidden.</div> <div class="longdesc">Same as 'show( speed, [callback] )' without animations. Doesn't change anything if the selected elements are all visible. It doesn't matter if the element is hidden via a hide() call, or via a display:none in a stylesheet.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					显示所有段落
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p style="display: none"&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").show() 
				</div>
			</div>
			<div class="functionitem">show(speed,callback)</div>
			<div class="content">
				<h1>show(speed,[callback])</h1>
				<div class="desc"><div>以优雅的动画显示所有匹配的元素，并在显示完成后可选地触发一个回调函数。</div> <div class="longdesc">可以根据指定的速度动态地改变每个匹配元素的高度、宽度和不透明度。在jQuery 1.3中，padding和margin也会有动画，效果更流畅。</div></div><hr />
				<div class="desc"><div>Show all matched elements using a graceful animation and firing an optional callback after completion.</div> <div class="longdesc">The height, width, and opacity of each of the matched elements are changed dynamically according to the specified speed.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) : 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>callback </strong>(Function) : (Optional) 在动画完成时执行的函数，每个元素执行一次。</p>
				<h2>示例</h2>
				<p class="indent">
					用缓慢的动画将隐藏的段落显示出来，历时600毫秒。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p style="display: none"&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").show("slow"); 
				</div>
				<hr />
				<p class="indent">
					用迅速的动画将隐藏的段落显示出来，历时200毫秒。并在之后执行反馈！
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p style="display: none"&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").show("fast",function(){<br/>
&nbsp;&nbsp; $(this).text("Animation Done!");<br/>
&nbsp;});
				</div>
				<hr />
				<p class="indent">
					将隐藏的段落用将近4秒的时间显示出来。。。并在之后执行一个反馈。。。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p style="display: none"&gt;Hello&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").show(4000,function(){<br/>
&nbsp;&nbsp; $(this).text("Animation Done...");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">hide()</div>
			<div class="content">
				<h1>hide()</h1>
				<div class="desc"><div>隐藏显示的元素</div> <div class="longdesc">这个就是 'hide( speed, [callback] )' 的无动画版。如果选择的元素是隐藏的，这个方法将不会改变任何东西。</div></div><hr />
				<div class="desc"><div>Hides each of the set of matched elements if they are shown.</div> <div class="longdesc">Same as 'hide( speed, [callback] )' without animations. Doesn't change anything if the selected elements are all hidden.</div></div>

				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					隐藏所有段落
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").hide() 
				</div>
			</div>
			<div class="functionitem">hide(speed,callback)</div>
			<div class="content">
				<h1>hide(speed,[callback])</h1>
				<div class="desc"><div>以优雅的动画隐藏所有匹配的元素，并在显示完成后可选地触发一个回调函数。</div> <div class="longdesc">可以根据指定的速度动态地改变每个匹配元素的高度、宽度和不透明度。在jQuery 1.3中，padding和margin也会有动画，效果更流畅。</div></div><hr />
				<div class="desc"><div>Hide all matched elements using a graceful animation and firing an optional callback after completion.</div> <div class="longdesc">The height, width, and opacity of each of the matched elements are changed dynamically according to the specified speed.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) :三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000) </p>
				<p class="indent"><strong>callback </strong>(FunctionFunction) : (Optional) 在动画完成时执行的函数，每个元素执行一次。</p>
				<h2>示例</h2>
				<p class="indent">
					用600毫秒的时间将段落缓慢的隐藏
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").hide("slow"); 
				</div>
				<hr />
				<p class="indent">
					用200毫秒将段落迅速隐藏，之后弹出一个对话框。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").hide("fast",function(){<br/>
&nbsp;&nbsp; alert("Animation Done.");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">toggle()</div>
			<div class="content">
				<h1>toggle()</h1>
				<div class="desc"><div>切换元素的可见状态。</div> <div class="longdesc">如果元素是可见的，切换为隐藏的；如果元素是隐藏的，切换为可见的。</div></div><hr />
				<div class="desc"><div>Toggles each of the set of matched elements.</div> <div class="longdesc">If they are shown, toggle makes them hidden. If they are hidden, toggle makes them shown.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					切换所有段落的可见状态。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p style="display: none"&gt;Hello Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").toggle() 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p tyle="display: none"&gt;Hello&lt;/p&gt;&lt;p style="display: block"&gt;Hello Again&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">toggle( switch )</div>
			<div class="content">
				<h1>toggle( switch )</h1>
				<div class="desc"><div>根据switch参数切换元素的可见状态（ture为可见，false为隐藏）。</div> <div class="longdesc">如果switch设为true，则调用show()方法来显示匹配的元素，如果switch设为false则调用hide()来隐藏元素。</div></div><hr />
				<div class="desc"><div>Toggle displaying each of the set of matched elements based upon the switch (true shows all elements, false hides all elements).</div> <div class="longdesc">If the switch is true, toggle makes them hidden (using the hide method). If the switch is false, toggle makes them shown (using the show method).</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					切换所有段落的可见状态。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p&gt;Hello&lt;/p&gt;&lt;p style="display: none"&gt;Hello Again&lt;/p&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
&nbsp;&nbsp;var flip = 0;<br />
&nbsp;&nbsp;$("button").click(function () {<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("p").toggle( flip++ % 2 == 0 );<br />
&nbsp;&nbsp;});<br />
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;p tyle="display: none"&gt;Hello&lt;/p&gt;&lt;p style="display: block"&gt;Hello Again&lt;/p&gt; 
				</div>
			</div>
			<div class="functionitem">toggle(speed,callback)</div>
			<div class="content">
				<h1>toggle(speed,[callback])</h1>
				<div class="desc"><div>以优雅的动画切换所有匹配的元素，并在显示完成后可选地触发一个回调函数。</div> <div class="longdesc">可以根据指定的速度动态地改变每个匹配元素的高度、宽度和不透明度。在jQuery 1.3中，padding和margin也会有动画，效果更流畅。</div></div><hr />
				<div class="desc"><div>Toggle displaying each of the set of matched elements using a graceful animation and firing an optional callback after completion.</div> <div class="longdesc">The height, width, and opacity of each of the matched elements are changed dynamically according to the specified speed. As of jQuery 1.3 the padding and margin are also animated, creating a smoother effect.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) :三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000) </p>
				<p class="indent"><strong>callback </strong>(FunctionFunction) : (Optional) 在动画完成时执行的函数，每个元素执行一次。</p>
				<h2>示例</h2>
				<p class="indent">
					用600毫秒的时间将段落缓慢的切换显示状态
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").toggle("slow"); 
				</div>
				<hr />
				<p class="indent">
					用200毫秒将段落迅速切换显示状态，之后弹出一个对话框。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").toggle("fast",function(){<br/>
&nbsp;&nbsp; alert("Animation Done.");<br/>
&nbsp;});
				</div>
			</div>
		</div>
		<div class="categoryitem">滑动</div>
		<div class="category">
			<div class="functionitem">slideDown(speed,callback)</div>
			<div class="content">
				<h1>slideDown(speed,[callback])</h1>
				<div class="desc"><div>通过高度变化（向下增大）来动态地显示所有匹配的元素，在显示完成后可选地触发一个回调函数。</div> <div class="longdesc">这个动画效果只调整元素的高度，可以使匹配的元素以“滑动”的方式显示出来。在jQuery 1.3中，上下的padding和margin也会有动画，效果更流畅。</div></div><hr />
				<div class="desc"><div>Reveal all matched elements by adjusting their height and firing an optional callback after completion.</div> <div class="longdesc">Only the height is adjusted for this animation, causing all matched elements to be revealed in a "sliding" manner.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) :三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>callback </strong>(FunctionFunction) : (可选) 在动画完成时执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					用600毫秒缓慢的将段落滑下
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slideDown("slow"); 
				</div>
				<hr />
				<p class="indent">
					用200毫秒快速将段落滑下，之后弹出一个对话框
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slideDown("fast",function(){<br/>
&nbsp;&nbsp; alert("Animation Done.");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">slideUp(speed,callback)</div>
			<div class="content">
				<h1>slideUp(speed,[callback])</h1>
				<div class="desc"><div>通过高度变化（向上减小）来动态地隐藏所有匹配的元素，在隐藏完成后可选地触发一个回调函数。</div> <div class="longdesc">这个动画效果只调整元素的高度，可以使匹配的元素以“滑动”的方式隐藏起来。在jQuery 1.3中，上下的padding和margin也会有动画，效果更流畅。</div></div><hr />
				<div class="desc"><div>Hide all matched elements by adjusting their height and firing an optional callback after completion.</div> <div class="longdesc">Only the height is adjusted for this animation, causing all matched elements to be hidden in a "sliding" manner.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) : 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 在动画完成时执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					用600毫秒缓慢的将段落滑上
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slideUp("slow"); 
				</div>
				<hr />
				<p class="indent">
					用200毫秒快速将段落滑上，之后弹出一个对话框
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slideUp("fast",function(){<br/>
&nbsp;&nbsp; alert("Animation Done.");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">slideToggle(speed,callback)</div>
			<div class="content">
				<h1>slideToggle(speed,[callback])</h1>
				<div class="desc"><div>通过高度变化来切换所有匹配元素的可见性，并在切换完成后可选地触发一个回调函数。</div> <div class="longdesc">这个动画效果只调整元素的高度，可以使匹配的元素以“滑动”的方式隐藏或显示。在jQuery 1.3中，上下的padding和margin也会有动画，效果更流畅。</div></div><hr />
				<div class="desc"><div>Toggle the visibility of all matched elements by adjusting their height and firing an optional callback after completion.</div> <div class="longdesc">Only the height is adjusted for this animation, causing all matched elements to be hidden or shown in a "sliding" manner.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) :  三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 在动画完成时执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					用600毫秒缓慢的将段落滑上或滑下
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slideToggle("slow"); 
				</div>
				<hr />
				<p class="indent">
					用200毫秒快速将段落滑上或滑下，之后弹出一个对话框
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").slideToggle("fast",function(){<br/>
&nbsp;&nbsp; alert("Animation Done.");<br/>
&nbsp;});
				</div>
			</div>
		</div>
		<div class="categoryitem">淡入淡出</div>
		<div class="category">
			<div class="functionitem">fadeIn(speed,callback)</div>
			<div class="content">
				<h1>fadeIn(speed,[callback])</h1>
				<div class="desc"><div>通过不透明度的变化来实现所有匹配元素的淡入效果，并在动画完成后可选地触发一个回调函数。</div> <div class="longdesc">这个动画只调整元素的不透明度，也就是说所有匹配的元素的高度和宽度不会发生变化。</div></div><hr />
				<div class="desc"><div>Fade in all matched elements by adjusting their opacity and firing an optional callback after completion.</div> <div class="longdesc">Only the opacity is adjusted for this animation, meaning that all of the matched elements should already have some form of height and width associated with them.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) : 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>callback </strong>(Function) : (Optional) (可选) 在动画完成时执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					用600毫秒缓慢的将段落淡入
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").fadeIn("slow"); 
				</div>
				<hr />
				<p class="indent">
					用200毫秒快速将段落淡入，之后弹出一个对话框
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").fadeIn("fast",function(){<br/>
&nbsp;&nbsp; alert("Animation Done.");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">fadeOut(speed,callback)</div>
			<div class="content">
				<h1>fadeOut(speed,[callback])</h1>
				<div class="desc"><div>通过不透明度的变化来实现所有匹配元素的淡出效果，并在动画完成后可选地触发一个回调函数。</div> <div class="longdesc">这个动画只调整元素的不透明度，也就是说所有匹配的元素的高度和宽度不会发生变化。</div></div><hr />
				<div class="desc"><div>Fade out all matched elements by adjusting their opacity and firing an optional callback after completion.</div> <div class="longdesc">Only the opacity is adjusted for this animation, meaning that all of the matched elements should already have some form of height and width associated with them.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) :三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>callback </strong>(Function) :(可选) 在动画完成时执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					用600毫秒缓慢的将段落淡出
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").fadeOut("slow"); 
				</div>
				<hr />
				<p class="indent">
					用200毫秒快速将段落淡出，之后弹出一个对话框
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").fadeOut("fast",function(){<br/>
&nbsp;&nbsp; alert("Animation Done.");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">fadeTo(speed,opacity,callback)</div>
			<div class="content">
				<h1>fadeTo(speed,opacity,[callback])</h1>
				<div class="desc"><div>把所有匹配元素的不透明度以渐进方式调整到指定的不透明度，并在动画完成后可选地触发一个回调函数。</div> <div class="longdesc">这个动画只调整元素的不透明度，也就是说所有匹配的元素的高度和宽度不会发生变化。</div></div><hr />
				<div class="desc"><div>Fade the opacity of all matched elements to a specified opacity and firing an optional callback after completion.</div> <div class="longdesc">Only the opacity is adjusted for this animation, meaning that all of the matched elements should already have some form of height and width associated with them.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>speed </strong>(String,Number) : 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>opacity </strong>(Number) : 要调整到的不透明度值(0到1之间的数字).</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 在动画完成时执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					用600毫秒缓慢的将段落的透明度调整到0.66，大约2/3的可见度
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").fadeTo("slow", 0.66);$("p").fadeTo("slow", 0.66); 
				</div>
				<hr />
				<p class="indent">
					用200毫秒快速将段落的透明度调整到0.25，大约1/4的可见度，之后弹出一个对话框
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").fadeTo("fast", 0.25, function(){<br/>
&nbsp;&nbsp; alert("Animation Done.");<br/>
&nbsp;});
				</div>
			</div>
		</div>
		<div class="categoryitem">自定义</div>
		<div class="category">
			<div class="functionitem littletext">animate(params,duration,easing,callback)</div>
			<div class="content">
				<h1>animate(params[,duration[,easing[,callback]]])</h1>
				<div class="desc"><div>用于创建自定义动画的函数。</div> <div class="longdesc">这个函数的关键在于指定动画形式及结果样式属性对象。这个对象中每个属性都表示一个可以变化的样式属性（如“height”、“top”或“opacity”）。<p>注意：所有指定的属性必须用骆驼形式，比如用marginLeft代替margin-left. </p><p>而每个属性的值表示这个样式属性到多少时动画结束。如果是一个数值，样式属性就会从当前的值渐变到指定的值。如果使用的是“hide”、“show”或“toggle”这样的字符串值，则会为该属性调用默认的动画形式。</p><p>在 jQuery 1.2 中，你可以使用 em 和 % 单位。另外，在 jQuery 1.2 中，你可以通过在属性值前面指定 "<em>+=</em>" 或 "<em>-=</em>" 来让元素做相对运动。</p><p>jQuery 1.3中，如果duration设为0则直接完成动画。而在以前版本中则会执行默认动画。</p></div></div><hr />
				<div class="desc"><div>A function for making your own, custom animations.</div> <div class="longdesc">The key aspect of this function is the object of style properties that will be animated, and to what end. Each key within the object represents a style property that will also be animated (for example: "height", "top", or "opacity"). <p>Note that properties should be specified using camel case eg. marginLeft instead of margin-left. </p><p>The value associated with the key represents to what end the property will be animated. If a number is provided as the value, then the style property will be transitioned from its current state to that new number. Otherwise if the string "hide", "show", or "toggle" is provided, a default animation will be constructed for that property. </p><p> As of jQuery 1.2 you can now animate properties by em and % (where applicable). Additionally, in jQuery 1.2, you can now do relative animations - specifying a "<em>+=</em>" or "<em>-=</em>" in front of the property value to move the element positively, or negatively, relative to the current position.</p><p>As of jQuery 1.3 if you specify an animation duration of 0 then the animation will synchronously set the elements to their end state (this is different from old versions where there would be a brief, asynchronous, delay before the end state would be set).</p></div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>params </strong>(Options) : 一组包含作为动画属性和终值的样式属性和及其值的集合</p>
				<p class="indent"><strong>duration </strong>(String,Number) : (可选) 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>easing </strong>(String) : (可选) 要使用的擦除效果的名称(需要插件支持).默认jQuery提供"linear" 和 "swing".</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 在动画完成时执行的函数</p>
				<h2>示例</h2>
				<p class="indent">
					点击按钮后div元素的几个不同属性一同变化
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;button id="go"&gt; Run&lt;/button&gt;<br/>
&lt;div id="block"&gt;Hello!&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					// 在一个动画中同时应用三种类型的效果<br/>
$("#go").click(function(){<br/>
&nbsp; $("#block").animate({ <br/>
&nbsp;&nbsp;&nbsp; width: "90%",<br/>
&nbsp;&nbsp;&nbsp; height: "100%", <br/>
&nbsp;&nbsp;&nbsp; fontSize: "10em", <br/>
&nbsp;&nbsp;&nbsp; borderWidth: 10<br/>
&nbsp; }, 1000 );<br/>
});
				</div>
				<hr />
				<p class="indent">
					让指定元素左右移动
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;button id="left"&gt;«&lt;/button&gt; &lt;button id="right"&gt;»&lt;/button&gt;<br/>
&lt;div class="block"&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#right").click(function(){<br/>
&nbsp; $(".block").animate({left: '+50px'}, "slow");<br/>
});<br/>
<br/>
$("#left").click(function(){<br/>
&nbsp; $(".block").animate({left: '-50px'}, "slow");<br/>
});
				</div>
				<hr />
				<p class="indent">
					在600毫秒内切换段落的高度和透明度
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").animate({<br/>
&nbsp;&nbsp; height: 'toggle', opacity: 'toggle'<br/>
&nbsp;}, "slow"); 
				</div>
				<hr />
				<p class="indent">
					用500毫秒将段落移到left为50的地方并且完全清晰显示出来（透明度为1） 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").animate({<br/>
&nbsp;&nbsp; left: 50, opacity: 'show'<br/>
&nbsp;}, 500); 
				</div>
				<hr />
				<p class="indent">
					一个使用“easein”函数提供不同动画样式的例子。只有使用了插件来提供这个“easein”函数，这个参数才起作用。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").animate({<br/>
&nbsp;&nbsp; opacity: 'show'<br/>
&nbsp;}, "slow", "easein"); 
				</div>
			</div>
			<div class="functionitem">animate(params,options)</div>
			<div class="content">
				<h1>animate(params,options)</h1>
				<div class="desc"><div>用于创建自定义动画的函数。</div> <div class="longdesc">这个函数的关键在于指定动画形式及结果样式属性对象。这个对象中每个属性都表示一个可以变化的样式属性（如“height”、“top”或“opacity”）。<p>注意：所有指定的属性必须用骆驼形式，比如用marginLeft代替margin-left. </p><p>而每个属性的值表示这个样式属性到多少时动画结束。如果是一个数值，样式属性就会从当前的值渐变到指定的值。如果使用的是“hide”、“show”或“toggle”这样的字符串值，则会为该属性调用默认的动画形式。</p><p>在 jQuery 1.2 中，你可以使用 em 和 % 单位。另外，在 jQuery 1.2 中，你可以通过在属性值前面指定 "<em>+=</em>" 或 "<em>-=</em>" 来让元素做相对运动。</p></div></div><hr />
				<div class="desc"><div>A function for making your own, custom animations.</div> <div class="longdesc">The key aspect of this function is the object of style properties that will be animated, and to what end. Each key within the object represents a style property that will also be animated (for example: "height", "top", or "opacity"). <p>Note that properties should be specified using camel case eg. marginLeft instead of margin-left. </p>The value associated with the key represents to what end the property will be animated. If a number is provided as the value, then the style property will be transitioned from its current state to that new number. Otherwise if the string "hide", "show", or "toggle" is provided, a default animation will be constructed for that property.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>params </strong>(Options) : 一组包含作为动画属性和终值的样式属性和及其值的集合</p>
				<p class="indent"><strong>options </strong>(Options) : 一组包含动画选项的值的集合。</p>
				<h2>选项</h2>
				<p class="indent"><strong>duration </strong>(String,Number) : (默认值: "normal") 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</p>
				<p class="indent"><strong>easing </strong>(String) : (默认值: "swing") 要使用的擦除效果的名称(需要插件支持).默认jQuery提供"linear" 和 "swing".</p>
				<p class="indent"><strong>complete </strong>(Function) : 在动画完成时执行的函数</p>
				<p class="indent"><strong>step </strong>(Callback) : </p>
				<p class="indent"><strong>queue </strong>(Boolean) : (默认值: true) 设定为false将使此动画不进入动画队列 (jQuery 1.2中新增)</p>
				<h2>示例</h2>
				<p class="indent">
					第一个按钮按了之后展示了不在队列中的动画。在div扩展到90%的同时也在增加字体，一旦字体改变完毕后，边框的动画才开始。
				</p><p>
					第二个按钮按了之后就是一个传统的链式动画，即等前一个动画完成后，后一个动画才会开始.
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;button id="go1"&gt;» Animate Block1&lt;/button&gt;<br/>
&lt;button id="go2"&gt;» Animate Block2&lt;/button&gt;<br/>
&lt;div id="block1"&gt;Block1&lt;/div&gt;&lt;div id="block2"&gt;Block2&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#go1").click(function(){<br/>
&nbsp; $("#block1").animate( { width: "90%"}, { queue: false, duration: 5000 } )<br/>
&nbsp;&nbsp;&nbsp;&nbsp; .animate( { fontSize: '10em' } , 1000 )<br/>
&nbsp;&nbsp;&nbsp;&nbsp; .animate( { borderWidth: 5 }, 1000);<br/>
});<br/>
<br/>
$("#go2").click(function(){<br/>
&nbsp; $("#block2").animate( { width: "90%"}, 1000 )<br/>
&nbsp;&nbsp;&nbsp;&nbsp; .animate( { fontSize: '10em' } , 1000 )<br/>
&nbsp;&nbsp;&nbsp;&nbsp; .animate( { borderWidth: 5 }, 1000);<br/>
});
				</div>
				<hr />
				<p class="indent">
					用600毫秒切换段落的高度和透明度
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").animate({<br/>
&nbsp;&nbsp; height: 'toggle', opacity: 'toggle'<br/>
&nbsp;}, { duration: "slow" });
				</div>
				<hr />
				<p class="indent">
					用500毫秒将段落移到left为50的地方并且完全清晰显示出来（透明度为1） 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").animate({<br/>
&nbsp;&nbsp; left: 50, opacity: 'show'<br/>
&nbsp;}, { duration: 500 });<br/>
				</div>
				<hr />
				<p class="indent">
					一个使用“easein”函数提供不同动画样式的例子。只有使用了插件来提供这个“easein”函数，这个参数才起作用。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("p").animate({<br/>
&nbsp;&nbsp; opacity: 'show'<br/>
&nbsp;}, { duration: "slow", easing: "easein" });
				</div>
			</div>
			<div class="functionitem">stop( [clearQueue], [gotoEnd])</div>
			<div class="content">
				<h1>stop( [clearQueue], [gotoEnd])</h1>
				<div class="desc"><div>停止所有在指定元素上正在运行的动画。</div> <div class="longdesc">如果队列中有等待执行的动画(并且clearQueue没有设为true)，他们将被马上执行</div></div><hr />
				<div class="desc"><div>Stops all the currently running animations on all the specified elements.</div> <div class="longdesc">If any animations are queued to run, then they will begin immediately.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>clearQueue </strong>(Boolean) : 可选，如果设置成true，则清空队列。可以立即结束动画。</p>
				<p class="indent"><strong>gotoEnd </strong>(Boolean) : 可选，让当前正在执行的动画立即完成，并且重设show和hide的原始样式，调用回调函数等。</p>
				<h2>示例</h2>
				<p class="indent">
					点击Go之后开始动画,点Stop之后会在当前位置停下来
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;button id="go"&gt;Go&lt;/button&gt; &lt;button <br/>id="stop"&gt;STOP!&lt;/button&gt;<br/>
&lt;div class="block"&gt;&lt;/div&gt;<br/>&lt;button id="go"&gt;Go&lt;/button&gt; &lt;button <br/>id="stop"&gt;STOP!&lt;/button&gt;<br/>
&lt;div class="block"&gt;&lt;/div&gt;<br/>
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					// 开始动画<br/>
$("#go").click(function(){<br/>
&nbsp; $(".block").animate({left: '+200px'}, 5000);<br/>
});<br/>
<br/>
// 当点击按钮后停止动画<br/>
$("#stop").click(function(){<br/>
&nbsp; $(".block").stop();<br/>
});
				</div>
			</div>
		</div>
		<div class="categoryitem">设置</div>
		<div class="category">
			<div class="functionitem">jQuery.fx.off</div>
			<div class="content">
				<h1>jQuery.fx.off</h1>
				<div class="desc"><div>关闭页面上所有的动画。</div> <div class="longdesc"><p>把这个属性设置为true可以立即关闭所有动画(所有效果会立即执行完毕)。有些情况下可能需要这样，比如：</p><p>* 你在配置比较低的电脑上使用jQuery。</p><p>* 你的一些用户由于动画效果而遇到了 <a href="http://www.jdeegan.phlegethon.org/turn_off_animation.html">可访问性问题</a></p><p>当把这个属性设成false之后，可以重新开启所有动画。</p></div></div><hr />
				<div class="desc"><div>Globally disable all animations.</div> <div class="longdesc"><p>Setting this property to true will disable all animations from occurring (the effect will happen instantaneously, instead). This may be desirable for a couple reasons:</p><p>You're using jQuery on a low-resource device.</p><p>Some of your users are encountering <a href="http://www.jdeegan.phlegethon.org/turn_off_animation.html">accessibility problems</a> with the animations.</p><p>Animations can be turned back on by setting the property to false.</p>
				</div></div>
				<h2>返回值</h2>
				<p class="indent">Boolean</p>
				<h2>示例</h2>
				<p class="indent">
					执行一个禁用的动画
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
&nbsp;&nbsp;jQuery.fx.off = true;<br />
&nbsp;&nbsp;$("input").click(function(){<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$("div").toggle("slow");<br />
&nbsp;&nbsp;});<br />
				</div>
			</div>
		</div>
	</div>
	<!-- Ajax -->
	<div class="menuitem">Ajax</div>
	<div class="functionmenu">
		<div class="categoryitem">Ajax 请求</div>
		<div class="category">
			<div class="functionitem">jQuery.ajax(options)</div>
			<div class="content">
				<h1>jQuery.ajax(options)</h1>
				<div class="desc"><div>通过 HTTP 请求加载远程数据。</div> <div class="longdesc">jQuery 底层 AJAX 实现。简单易用的高层实现见 $.get, $.post 等。<p>$.ajax() 返回其创建的 XMLHttpRequest 对象。大多数情况下你无需直接操作该对象，但特殊情况下可用于手动终止请求。</p><p>$.ajax() 只有一个参数：参数 key/value 对象，包含各配置及回调函数信息。详细参数选项见下。</p><p><strong>注意：</strong> 如果你指定了 dataType 选项，请确保服务器返回正确的 MIME 信息，(如 xml 返回 "text/xml")。错误的 MIME 类型可能导致不可预知的错误。见 <a href="http://docs.jquery.com/Specifying_the_Data_Type_for_AJAX_Requests" title="Specifying the Data Type for AJAX Requests">Specifying the Data Type for AJAX Requests</a> 。</p><p><strong>注意：</strong>如果dataType设置为"script"，那么在远程请求时(不在同一个域下)，所有POST请求都将转为GET请求。(因为将使用DOM的script标签来加载)</p><p>jQuery 1.2 中，您可以跨域加载 JSON 数据，使用时需将数据类型设置为 <a href="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/" class="external text" title="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/">JSONP</a>。使用 <a href="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/" class="external text" title="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/">JSONP</a> 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。数据类型设置为 "jsonp" 时，jQuery 将自动调用回调函数。</p></div></div>
				<hr />
				<div class="desc"><div>Load a remote page using an HTTP request.</div> <div class="longdesc">This is jQuery's low-level AJAX implementation. See $.get, $.post etc. for higher-level abstractions that are often easier to understand and use, but don't offer as much functionality (such as error callbacks). <p>$.ajax() returns the XMLHttpRequest that it creates. In most cases you won't need that object to manipulate directly, but it is available if you need to abort the request manually. </p><p><strong>Note:</strong> If you specify the dataType option described below, make sure the server sends the correct MIME type in the response (eg. xml as "text/xml"). Sending the wrong MIME type can lead to unexpected problems in your script. See <a href="http://docs.jquery.com/Specifying_the_Data_Type_for_AJAX_Requests" title="Specifying the Data Type for AJAX Requests">Specifying the Data Type for AJAX Requests</a> for more information. </p><p>$.ajax() takes one argument, an object of key/value pairs, that are used to initialize and handle the request. See below for a full list of the key/values that can be used. </p> As of jQuery 1.2, you can load JSON data located on another domain if you specify a <a href="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/" class="external text" title="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/">JSONP</a> callback, which can be done like so: "myurl?callback=?". jQuery automatically replaces the&nbsp;? with the correct method name to call, calling your specified callback. Or, if you set the dataType to "jsonp" a callback will be automatically added to your Ajax request.</div></div>
				<h2>返回值</h2>
				<p class="indent">XMLHttpRequest</p>
				<h2>参数</h2>
				<p class="indent"><strong>options </strong>(可选) : AJAX 请求设置。所有选项都是可选的。</p>
				<h2>选项</h2>
				<p class="indent"><strong>async </strong>(Boolean) : (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。</p>
				<p class="indent"><strong>beforeSend </strong>(Function) : 发送请求前可修改 XMLHttpRequest 对象的函数，如添加自定义 HTTP 头。XMLHttpRequest 对象是唯一的参数。这是一个 <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。如果返回false可以取消本次ajax请求。
				<div class="code">
					function (XMLHttpRequest) {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;this; // 调用本次AJAX请求时传递的options参数<br/>
}				</div></p>
				<p class="indent"><strong>cache </strong>(Boolean) : (默认: true,dataType为script时默认为false) jQuery 1.2 新功能，设置为 false 将不会从浏览器缓存中加载请求信息。</p>
				<p class="indent"><strong>complete </strong>(Function) : 请求完成后回调函数 (请求成功或失败时均调用)。参数：  XMLHttpRequest 对象和一个描述成功请求类型的字符串。 <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。 <div class="code">
					function (XMLHttpRequest, textStatus) {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;this; // 调用本次AJAX请求时传递的options参数<br/>
}				</div></p>
				<p class="indent"><strong>contentType </strong>(String) : (默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数应用场合。</p>
				<p class="indent"><strong>data </strong>(Object,String) : 发送到服务器的数据。将自动转换为请求字符串格式。GET 请求中将附加在 URL 后。查看 processData 选项说明以禁止此自动转换。必须为 Key/Value 格式。如果为数组，jQuery 将自动为不同值对应同一个名称。如 {foo:["bar1", "bar2"]} 转换为 '&amp;foo=bar1&amp;foo=bar2'。</p>
				<p class="indent"><strong>dataFilter </strong>(Function) :给Ajax返回的原始数据的进行预处理的函数。提供data和type两个参数：data是Ajax返回的原始数据，type是调用jQuery.ajax时提供的dataType参数。函数返回的值将由jQuery进一步处理。
				<div class="code">
					function (data, type) {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;// 对Ajax返回的原始数据进行预处理<br/>
&nbsp;&nbsp;&nbsp;&nbsp;return data  // 返回处理后的数据<br/>
}				</div></p>
				<p class="indent"><strong>dataType </strong>(String) : (默认值：智能判断xml或者html)预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息返回 responseXML 或 responseText，并作为回调函数参数传递，可用值: <p>"xml": 返回 XML 文档，可用 jQuery 处理。</p><p>"html": 返回纯文本 HTML 信息；包含的script标签会在插入dom时执行。</p><p>"script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了"cache"参数。<strong>注意：</strong>在远程请求时(不在同一个域下)，所有POST请求都将转为GET请求。(因为将使用DOM的script标签来加载)</p><p>"json": 返回 JSON 数据 。</p><p>"jsonp": <a href="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/" class="external text" title="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/">JSONP</a> 格式。使用 <a href="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/" class="external text" title="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/">JSONP</a> 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。</p><p>"text": 返回纯文本字符串</p></p>
				<p class="indent"><strong>error </strong>(Function) : (默认: 自动判断 (xml 或 html)) 请求失败时调用时间。参数有以下三个：XMLHttpRequest 对象、错误信息、（可选）捕获的错误对象。如果发生了错误，错误信息（第二个参数）除了得到null之外，还可能是"timeout", "error", "notmodified" 和 "parsererror"。<a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。 <div class="code">
					function (XMLHttpRequest, textStatus, errorThrown) {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;// 通常 textStatus 和 errorThrown 之中<br/>
&nbsp;&nbsp;&nbsp;&nbsp;// 只有一个会包含信息<br/>
&nbsp;&nbsp;&nbsp;&nbsp;this; // 调用本次AJAX请求时传递的options参数<br/>
}				</div></p>
				<p class="indent"><strong>global </strong>(Boolean) : (默认: true) 是否触发全局 AJAX 事件。设置为 false 将不会触发全局 AJAX 事件，如 ajaxStart 或 ajaxStop 可用于控制不同的 <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。</p>
				<p class="indent"><strong>ifModified </strong>(Boolean) : (默认: false) 仅在服务器数据改变时获取新数据。使用 HTTP 包 Last-Modified 头信息判断。</p>
				<p class="indent"><strong>jsonp </strong>(String) : 在一个jsonp请求中重写回调函数的名字。这个值用来替代在"callback=?"这种GET或POST请求中URL参数里的"callback"部分，比如{jsonp:'onJsonPLoad'}会导致将"onJsonPLoad=?"传给服务器。</p>
				<p class="indent"><strong>password </strong>(String) : 用于响应HTTP访问认证请求的密码</p>
				<p class="indent"><strong>processData </strong>(Boolean) : (默认: true) 默认情况下，发送的数据将被转换为对象(技术上讲并非字符串) 以配合默认内容类型 "application/x-www-form-urlencoded"。如果要发送 DOM 树信息或其它不希望转换的信息，请设置为 false。</p>
				<p class="indent"><strong>scriptCharset </strong>(String) : 只有当请求时dataType为"jsonp"或"script"，并且type是"GET"才会用于强制修改charset。通常在本地和远程的内容编码不同时使用。</p>
				<p class="indent"><strong>success </strong>(Function) : 请求成功后的回调函数。参数：由服务器返回，并根据dataType参数进行处理后的数据；描述状态的字符串。 <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。 <div class="code">
					function (data, textStatus) {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;// data 可能是 xmlDoc, jsonObj, html, text, 等等...<br/>
&nbsp;&nbsp;&nbsp;&nbsp;this; // 调用本次AJAX请求时传递的options参数<br/>
}				</div></p>
				<p class="indent"><strong>timeout </strong>(Number) : 设置请求超时时间（毫秒）。此设置将覆盖全局设置。</p>
				<p class="indent"><strong>type </strong>(String) : (默认: "GET") 请求方式 ("POST" 或 "GET")， 默认为 "GET"。注意：其它 HTTP 请求方法，如 PUT 和 DELETE 也可以使用，但仅部分浏览器支持。</p>
				<p class="indent"><strong>url </strong>(String) : (默认: 当前页地址) 发送请求的地址。</p>
				<p class="indent"><strong>username </strong>(String) : 用于响应HTTP访问认证请求的用户名</p>
				<p class="indent"><strong>xhr </strong>(Function) : 需要返回一个XMLHttpRequest 对象。默认在IE下是ActiveXObject 而其他情况下是XMLHttpRequest 。用于重写或者提供一个增强的XMLHttpRequest 对象。这个参数在jQuery 1.3以前不可用。</p>
				<h2>示例</h2>
				<p class="indent">
					加载并执行一个 JS 文件。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.ajax({<br/>
&nbsp; type: "GET",<br/>
&nbsp; url: "test.js",<br/>
&nbsp; dataType: "script"<br/>
});
				</div>
				<hr />
				<p class="indent">
					保存数据到服务器，成功时显示信息。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.ajax({<br/>
&nbsp;&nbsp; type: "POST",<br/>
&nbsp;&nbsp; url: "some.php",<br/>
&nbsp;&nbsp; data: "name=John&amp;location=Boston",<br/>
&nbsp;&nbsp; success: function(msg){<br/>
&nbsp;&nbsp;&nbsp;&nbsp; alert( "Data Saved: " + msg );<br/>
&nbsp;&nbsp; }<br/>
});
				</div>
				<hr />
				<p class="indent">
					装入一个 HTML 网页最新版本。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.ajax({<br/>
&nbsp; url: "test.html",<br/>
&nbsp; cache: false,<br/>
&nbsp; success: function(html){<br/>
&nbsp;&nbsp;&nbsp; $("#results").append(html);<br/>
&nbsp; }<br/>
});
				</div>
				<hr />
				<p class="indent">
					同步加载数据。发送请求时锁住浏览器。需要锁定用户交互操作时使用同步方式。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					&nbsp;var html = $.ajax({<br/>
&nbsp; url: "some.php",<br/>
&nbsp; async: false<br/>
&nbsp;}).responseText; 
				</div>
				<hr />
				<p class="indent">
					发送 XML 数据至服务器。设置 processData 选项为 false，防止自动转换数据格式。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					&nbsp;var xmlDocument = [create xml document];<br/>
&nbsp;$.ajax({<br/>
&nbsp;&nbsp; url: "page.php",<br/>
&nbsp;&nbsp; processData: false,<br/>
&nbsp;&nbsp; data: xmlDocument,<br/>
&nbsp;&nbsp; success: handleResponse<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">load(url,data,callback)</div>
			<div class="content">
				<h1>load(url,[data],[callback])</h1>
				<div class="desc"><div>载入远程 HTML 文件代码并插入至 DOM 中。</div> <div class="longdesc">默认使用 GET 方式 - 传递附加参数时自动转换为 POST 方式。jQuery 1.2 中，可以指定选择符，来筛选载入的 HTML 文档，DOM 中将仅插入筛选出的 HTML 代码。语法形如 "url #some &gt; selector"。请查看示例。</div></div>
				<hr />
				<div class="desc"><div>Load HTML from a remote file and inject it into the DOM.</div> <div class="longdesc">A GET request will be performed by default - but if you pass in any extra parameters then a POST will occur. In jQuery 1.2 you can now specify a jQuery selector in the URL. Doing so will filter the incoming HTML document, only injecting the elements that match the selector. The syntax looks something like "url #some &gt; selector". See the examples for more information.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>url </strong>(String) : 待装入 HTML 网页网址。</p>
				<p class="indent"><strong>data </strong>(Map,String) : (可选) 发送至服务器的 key/value 数据。在jQuery 1.3中也可以接受一个字符串了。</p>
				<p class="indent"><strong>callback </strong>(Callback) : (可选) 载入成功时回调函数。</p>
				<h2>示例</h2>
				<p class="indent">
					加载文章侧边栏导航部分至一个无序列表。 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;b&gt;jQuery Links:&lt;/b&gt;<br/>
&lt;ul id="links"&gt;&lt;/ul&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#links").load("/Main_Page #p-Getting-Started li"); 
				</div>
				<hr />
				<p class="indent">
					加载 feeds.html 文件内容。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#feeds").load("feeds.html"); 
				</div>
				<hr />
				<p class="indent">
					同上，但是以 POST 形式发送附加参数并在成功时显示信息。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					&nbsp;$("#feeds").load("feeds.php", {limit: 25}, function(){<br/>
&nbsp;&nbsp; alert("The last 25 entries in the feed have been loaded");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">jQuery.get(url,data,callback,type)</div>
			<div class="content">
				<h1>jQuery.get(url,[data],[callback],[type])</h1>
				<div class="desc"><div>通过远程 HTTP GET 请求载入信息。</div> <div class="longdesc">这是一个简单的 GET 请求功能以取代复杂 $.ajax 。请求成功时可调用回调函数。如果需要在出错时执行函数，请使用 $.ajax。</div></div>
				<hr />
				<div class="desc"><div>Load a remote page using an HTTP GET request.</div> <div class="longdesc">This is an easy way to send a simple GET request to a server without having to use the more complex $.ajax function. It allows a single callback function to be specified that will be executed when the request is complete (and only if the response has a successful response code). If you need to have both error and success callbacks, you may want to use $.ajax.</div></div>
				<h2>返回值</h2>
				<p class="indent">XMLHttpRequest</p>
				<h2>参数</h2>
				<p class="indent"><strong>url </strong>(String) : 待载入页面的URL地址</p>
				<p class="indent"><strong>data </strong>(Map) : (可选) 待发送 Key/value 参数。</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 载入成功时回调函数。</p>
				<p class="indent"><strong>type </strong>(String) : (可选) 返回内容格式，xml, html, script, json, text, _default。</p>
				<h2>示例</h2>
				<p class="indent">
					请求 test.php 网页，忽略返回值。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.get("test.php"); 
				</div>
				<hr />
				<p class="indent">
					请求 test.php 网页，传送2个参数，忽略返回值。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.get("test.php", { name: "John", time: "2pm" } ); 
				</div>
				<hr />
				<p class="indent">
					显示 test.php 返回值(HTML 或 XML，取决于返回值)。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.get("test.php", function(data){<br/>
&nbsp; alert("Data Loaded: " + data);<br/>
});
				</div>
				<hr />
				<p class="indent">
					显示 test.cgi 返回值(HTML 或 XML，取决于返回值)，添加一组请求参数。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.get("test.cgi", { name: "John", time: "2pm" },<br/>
&nbsp; function(data){<br/>
&nbsp;&nbsp;&nbsp; alert("Data Loaded: " + data);<br/>
&nbsp; });
				</div>
			</div>
			<div class="functionitem littletext">jQuery.getJSON(url,data,callback)</div>
			<div class="content">
				<h1>jQuery.getJSON(url,[data],[callback])</h1>
				<div class="desc"><div>通过 HTTP GET 请求载入 JSON 数据。</div> <div class="longdesc">在 jQuery 1.2 中，您可以通过使用<a href="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/" class="external text" title="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/">JSONP</a> 形式的回调函数来加载其他网域的JSON数据，如 "myurl?callback=?"。jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。 <p></p> 注意：此行以后的代码将在这个回调函数执行前执行。</div></div>
				<hr />
				<div class="desc"><div>Load JSON data using an HTTP GET request.</div> <div class="longdesc">As of jQuery 1.2, you can load JSON data located on another domain if you specify a <a href="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/" class="external text" title="http://bob.pythonmac.org/archives/2005/12/05/remote-json-jsonp/">JSONP</a> callback, which can be done like so: "myurl?callback=?". jQuery automatically replaces the&nbsp;? with the correct method name to call, calling your specified callback. <p></p> Note: Keep in mind, that lines after this function will be executed before callback.</div></div>
				<h2>返回值</h2>
				<p class="indent">XMLHttpRequest</p>
				<h2>参数</h2>
				<p class="indent"><strong>url </strong>(String) : 发送请求地址。</p>
				<p class="indent"><strong>data </strong>(Map) : (可选) 待发送 Key/value 参数。</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 载入成功时回调函数。</p>
				<h2>示例</h2>
				<p class="indent">
					从 Flickr JSONP API 载入 4 张最新的关于猫的图片。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;div id="images"&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.getJSON("http://api.flickr.com/services/feeds/photos_public.gne?tags=cat&amp;tagmode=any&amp;format=json&amp;jsoncallback=?",<br/> function(data){<br/>
&nbsp; $.each(data.items, function(i,item){<br/>
&nbsp;&nbsp;&nbsp; $("&lt;img/&gt;").attr("src", <br/>item.media.m).appendTo("#images");<br/>
&nbsp;&nbsp;&nbsp; if ( i == 3 ) return false;<br/>
&nbsp; });<br/>
});
				</div>
				<hr />
				<p class="indent">
					从 test.js 载入 JSON 数据并显示 JSON 数据中一个 name 字段数据。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.getJSON("test.js", function(json){<br/>
&nbsp; alert("JSON Data: " + json.users[3].name);<br/>
});
				</div>
				<hr />
				<p class="indent">
					从 test.js 载入 JSON 数据，附加参数，显示 JSON 数据中一个 name 字段数据。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.getJSON("test.js", { name: "John", time: "2pm" }, function(json){<br/>
&nbsp; alert("JSON Data: " + json.users[3].name);<br/>
});
				</div>
			</div>
			<div class="functionitem">jQuery.getScript(url,callback)</div>
			<div class="content">
				<h1>jQuery.getScript(url,[callback])</h1>
				<div class="desc"><div>通过 HTTP GET 请求载入并执行一个 JavaScript 文件。</div> <div class="longdesc">jQuery 1.2 版本之前，getScript 只能调用同域 JS 文件。 1.2中，您可以跨域调用 JavaScript 文件。注意：Safari 2 或更早的版本不能在全局作用域中同步执行脚本。如果通过 getScript 加入脚本，请加入延时函数。</div></div>
				<hr />
				<div class="desc"><div>Loads, and executes, a local JavaScript file using an HTTP GET request.</div> <div class="longdesc">Before jQuery 1.2, getScript was only able to load scripts from the same domain as the original page. As of 1.2, you can now load JavaScript files from any domain. Warning: Safari 2 and older is unable to evaluate scripts in a global context synchronously. If you load functions via getScript, make sure to call them after a delay.</div></div>
				<h2>返回值</h2>
				<p class="indent">XMLHttpRequest</p>
				<h2>参数</h2>
				<p class="indent"><strong>url </strong>(String) : 待载入 JS 文件地址。</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 成功载入后回调函数。</p>
				<h2>示例</h2>
				<p class="indent">
					载入 <a title="http://jquery.com/plugins/project/color" class="external text" href="http://jquery.com/plugins/project/color">jQuery 官方颜色动画插件</a> 成功后绑定颜色变化动画。
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;button id="go"&gt;» Run&lt;/button&gt;<br/>
&lt;div class="block"&gt;&lt;/div&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					jQuery.getScript("http://dev.jquery.com/view/trunk/plugins/color/jquery.color.js", <br/>function(){<br/>
&nbsp; $("#go").click(function(){<br/>
&nbsp;&nbsp;&nbsp; $(".block").animate( { backgroundColor: 'pink' }, 1000)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .animate( { backgroundColor: 'blue' }, 1000);<br/>
&nbsp; });<br/>
});
				</div>
				<hr />
				<p class="indent">
					加载并执行 test.js。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.getScript("test.js"); 
				</div>
				<hr />
				<p class="indent">
					加载并执行 test.js ，成功后显示信息。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.getScript("test.js", function(){<br/>
&nbsp; alert("Script loaded and executed.");<br/>
});
				</div>
			</div>
			<div class="functionitem">jQuery.post(url,data,callback,type)</div>
			<div class="content">
				<h1>jQuery.post(url,[data],[callback],[type])</h1>
				<div class="desc"><div>通过远程 HTTP POST 请求载入信息。</div> <div class="longdesc">这是一个简单的 POST 请求功能以取代复杂 $.ajax 。请求成功时可调用回调函数。如果需要在出错时执行函数，请使用 $.ajax。</div></div>
				<hr />
				<div class="desc"><div>Load a remote page using an HTTP POST request.</div> <div class="longdesc">This is an easy way to send a simple POST request to a server without having to use the more complex $.ajax function. It allows a single callback function to be specified that will be executed when the request is complete (and only if the response has a successful response code). If you need to have both error and success callbacks, you may want to use $.ajax.</div></div>
				<h2>返回值</h2>
				<p class="indent">XMLHttpRequest</p>
				<h2>参数</h2>
				<p class="indent"><strong>url </strong>(String) : 发送请求地址。</p>
				<p class="indent"><strong>data </strong>(Map) : (可选) 待发送 Key/value 参数。</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 发送成功时回调函数。</p>
				<p class="indent"><strong>type </strong>(String) : (可选) 返回内容格式，xml, html, script, json, text, _default。</p>
			</div>
		</div>
		<div class="categoryitem">Ajax 事件</div>
		<div class="category">
			<div class="functionitem">ajaxComplete(callback)</div>
			<div class="content">
				<h1>ajaxComplete(callback)</h1>
				<div class="desc"><div>AJAX 请求完成时执行函数。<a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。</div> <div class="longdesc">XMLHttpRequest 对象和设置作为参数传递给回调函数。</div></div>
				<hr />
				<div class="desc"><div>Attach a function to be executed whenever an AJAX request completes. This is an <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax Event</a>.</div> <div class="longdesc">The XMLHttpRequest and settings used for that request are passed as arguments to the callback.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 待执行函数</p>
				<h2>示例</h2>
				<p class="indent">
					AJAX 请求完成时执行函数。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					&nbsp;$("#msg").ajaxComplete(function(event,request, settings){<br/>
&nbsp;&nbsp; $(this).append("&lt;li&gt;请求完成.&lt;/li&gt;");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">ajaxError(callback)</div>
			<div class="content">
				<h1>ajaxError(callback)</h1>
				<div class="desc"><div>AJAX 请求发生错误时执行函数。<a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。</div> <div class="longdesc">XMLHttpRequest 对象和设置作为参数传递给回调函数。捕捉到的错误可作为最后一个参数传递。</div></div>
				<hr />
				<div class="desc"><div>Attach a function to be executed whenever an AJAX request fails. This is an <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax Event</a>.</div> <div class="longdesc">The XMLHttpRequest and settings used for that request are passed as arguments to the callback. A third argument, an exception object, is passed if an exception occured while processing the request.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 待执行函数<div class="code">
function (event, XMLHttpRequest, ajaxOptions, thrownError) {<br />
&nbsp;&nbsp; &nbsp;&nbsp; // thrownError 只有当异常发生时才会被传递<br />
&nbsp;&nbsp; &nbsp;&nbsp; this; // 监听的 dom 元素<br />
}</div>
</p>
				<h2>示例</h2>
				<p class="indent">
					AJAX 请求失败时显示信息。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#msg").ajaxError(function(event,request, settings){<br/>
&nbsp;&nbsp;&nbsp;&nbsp; $(this).append("&lt;li&gt;出错页面:" + settings.url + "&lt;/li&gt;");<br/>
});
				</div>
			</div>
			<div class="functionitem">ajaxSend(callback)</div>
			<div class="content">
				<h1>ajaxSend(callback)</h1>
				<div class="desc"><div>AJAX 请求发送前执行函数。<a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。</div> <div class="longdesc">XMLHttpRequest 对象和设置作为参数传递给回调函数。</div></div>
				<hr />
				<div class="desc"><div>Attach a function to be executed before an AJAX request is sent. This is an <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax Event</a>.</div> <div class="longdesc">The XMLHttpRequest and settings used for that request are passed as arguments to the callback.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 待执行函数</p>
				<h2>示例</h2>
				<p class="indent">
					AJAX 请求发送前显示信息。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					&nbsp;$("#msg").ajaxSend(function(evt, request, settings){<br/>
&nbsp;&nbsp; $(this).append("&lt;li&gt;开始请求: " + settings.url + <br/>"&lt;/li&gt;");<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">ajaxStart(callback)</div>
			<div class="content">
				<h1>ajaxStart(callback)</h1>
				<div class="desc">AJAX 请求开始时执行函数。<a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。</div>
				<hr />
				<div class="desc">Attach a function to be executed whenever an AJAX request begins and there is none already active. This is an <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax Event</a>.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 待执行函数</p>
				<h2>示例</h2>
				<p class="indent">
					AJAX 请求开始时显示信息。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					&nbsp;$("#loading").ajaxStart(function(){<br/>
&nbsp;&nbsp; $(this).show();<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">ajaxStop(callback)</div>
			<div class="content">
				<h1>ajaxStop(callback)</h1>
				<div class="desc">AJAX 请求结束时执行函数。<a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。</div>
				<hr />
				<div class="desc">Attach a function to be executed whenever all AJAX requests have ended. This is an <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax Event</a>.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 待执行函数</p>
				<h2>示例</h2>
				<p class="indent">
					AJAX 请求结束后隐藏信息。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					&nbsp;$("#loading").ajaxStop(function(){<br/>
&nbsp;&nbsp; $(this).hide();<br/>
&nbsp;});
				</div>
			</div>
			<div class="functionitem">ajaxSuccess(callback)</div>
			<div class="content">
				<h1>ajaxSuccess(callback)</h1>
				<div class="desc"><div>AJAX 请求成功时执行函数。<a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax 事件</a>。</div> <div class="longdesc">XMLHttpRequest 对象和设置作为参数传递给回调函数。</div></div>
				<hr />
				<div class="desc"><div>Attach a function to be executed whenever an AJAX request completes successfully. This is an <a href="http://docs.jquery.com/Ajax_Events" title="Ajax Events">Ajax Event</a>.</div> <div class="longdesc">The XMLHttpRequest and settings used for that request are passed as arguments to the callback.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>callback </strong>(Function) : 待执行函数</p>
				<h2>示例</h2>
				<p class="indent">
					当 AJAX 请求成功后显示消息。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					&nbsp;$("#msg").ajaxSuccess(function(evt, request, settings){<br/>
&nbsp;&nbsp; $(this).append("&lt;li&gt;请求成功!&lt;/li&gt;");<br/>
&nbsp;});
				</div>
			</div>
		</div>
		<div class="categoryitem">其它</div>
		<div class="category">
			<div class="functionitem">jQuery.ajaxSetup(options)</div>
			<div class="content">
				<h1>jQuery.ajaxSetup(options)</h1>
				<div class="desc"><div>设置全局 AJAX 默认选项。</div> <div class="longdesc">参数见 '$.ajax' 说明。</div></div>
				<hr />
				<div class="desc"><div>Setup global settings for AJAX requests.</div> <div class="longdesc">See '$.ajax' for a description of all available options.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>options </strong>(可选) : 选项设置。所有设置项均为可选设置。.</p>
				<h2>示例</h2>
				<p class="indent">
					设置 AJAX 请求默认地址为 "/xmlhttp/"，禁止触发全局 AJAX 事件，用 POST 代替默认 GET 方法。其后的 AJAX 请求不再设置任何选项参数。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.ajaxSetup({<br/>
&nbsp; url: "/xmlhttp/",<br/>
&nbsp; global: false,<br/>
&nbsp; type: "POST"<br/>
});<br/>
$.ajax({ data: myData });
				</div>
			</div>
			<div class="functionitem">serialize()</div>
			<div class="content">
				<h1>serialize()</h1>
				<div class="desc"><div>序列表表格内容为字符串。</div></div>
				<hr />
				<div class="desc"><div>Serializes a set of input elements into a string of data. This will serialize all given elements.</div></div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					序列表表格内容为字符串，用于 Ajax 请求。 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p id="results"&gt;&lt;b&gt;Results: &lt;/b&gt; &lt;/p&gt;<br/>
&lt;form&gt;<br/>
&nbsp; &lt;select name="single"&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option&gt;Single&lt;/option&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option&gt;Single2&lt;/option&gt;<br/>
&nbsp; &lt;/select&gt;<br/>
&nbsp; &lt;select name="multiple" multiple="multiple"&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option selected="selected"&gt;Multiple&lt;/option&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option&gt;Multiple2&lt;/option&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option selected="selected"&gt;Multiple3&lt;/option&gt;<br/>
&nbsp; &lt;/select&gt;&lt;br/&gt;<br/>
&nbsp; &lt;input type="checkbox" name="check" value="check1"/&gt; check1<br/>
&nbsp; &lt;input type="checkbox" name="check" value="check2" <br/>checked="checked"/&gt; check2<br/>
&nbsp; &lt;input type="radio" name="radio" value="radio1" <br/>checked="checked"/&gt; radio1<br/>
&nbsp; &lt;input type="radio" name="radio" value="radio2"/&gt; radio2<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("#results").append( "&lt;tt&gt;" + $("form").serialize() + "&lt;/tt&gt;" ); 
				</div>
			</div>
			<div class="functionitem">serializeArray()</div>
			<div class="content">
				<h1>serializeArray()</h1>
				<div class="desc">序列化表格元素 (类似 '.serialize()' 方法) 返回 JSON 数据结构数据。</div>
				<hr />
				<div class="desc">Serializes all forms and form elements (like the '.serialize()' method) but returns a JSON data structure for you to work with.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>示例</h2>
				<p class="indent">
					取得表单内容并插入到网页中。 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
					&lt;p id="results"&gt;&lt;b&gt;Results:&lt;/b&gt; &lt;/p&gt;<br/>
&lt;form&gt;<br/>
&nbsp; &lt;select name="single"&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option&gt;Single&lt;/option&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option&gt;Single2&lt;/option&gt;<br/>
&nbsp; &lt;/select&gt;<br/>
&nbsp; &lt;select name="multiple" multiple="multiple"&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option selected="selected"&gt;Multiple&lt;/option&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option&gt;Multiple2&lt;/option&gt;<br/>
&nbsp;&nbsp;&nbsp; &lt;option selected="selected"&gt;Multiple3&lt;/option&gt;<br/>
&nbsp; &lt;/select&gt;&lt;br/&gt;<br/>
&nbsp; &lt;input type="checkbox" name="check" value="check1"/&gt; check1<br/>
&nbsp; &lt;input type="checkbox" name="check" value="check2" <br/>checked="checked"/&gt; check2<br/>
&nbsp; &lt;input type="radio" name="radio" value="radio1" <br/>checked="checked"/&gt; radio1<br/>
&nbsp; &lt;input type="radio" name="radio" value="radio2"/&gt; radio2<br/>
&lt;/form&gt; 
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var fields = $("select, :radio").serializeArray();<br/>
jQuery.each( fields, function(i, field){<br/>
&nbsp; $("#results").append(field.value + " ");<br/>
});
				</div>
			</div>
		</div>
	</div>
	<!-- Utilities -->
	<div class="menuitem">工具</div>
	<div class="functionmenu">
		<div class="categoryitem">浏览器</div>
		<div class="category">
			<div class="functionitem">jQuery.support</div>
			<div class="content">
				<h1>jQuery.browser</h1>
				<div style="" id="overview" class="tabs-container">
					<div class="desc"><div>jQuery 1.3 新增。一组用于展示不同浏览器各自特性和bug的属性集合。</div> <div class="longdesc"><p>jQuery提供了一系列属性，你也可以自由增加你自己的属性。其中许多属性是很低级的，所以很难说他们能否在日新月异的发展中一直保持有效，但这这些主要用于插件和内核开发者。</p><p>所有这些支持的属性值都通过特性检测来实现，而不是用任何浏览器检测。以下有一些非常棒的资源用于解释这些特性检测是如何工作的：</p><ul>
						<li>http://peter.michaux.ca/articles/feature-detection-state-of-the-art-browser-scripting</li>
						<li>http://yura.thinkweb2.com/cft/</li>
						<li>http://www.jibbering.com/faq/faq_notes/not_browser_detect.html</li>
					</ul>
					<p></p>
					<p>jQuery.support主要包括以下测试：</p>
						<p><strong>boxModel</strong>: 如果这个页面和浏览器是以W3C CSS盒式模型来渲染的，则等于true。通常在IE 6和IE 7的怪癖模式中这个值是false。在document准备就绪前，这个值是null。</p>
						<p><strong>cssFloat</strong>: 如果用cssFloat来访问CSS的float的值，则返回true。目前在IE中会返回false,他用styleFloat代替。</p>
						<p><strong>hrefNormalized</strong>: 如果浏览器从getAttribute("href")返回的是原封不动的结果，则返回true。在IE中会返回false，因为他的URLs已经常规化了。</p>
						<p><strong>htmlSerialize</strong>: 如果浏览器通过innerHTML插入链接元素的时候会序列化这些链接，则返回true，目前IE中返回false。</p>
						<p><strong>leadingWhitespace</strong>: 如果在使用innerHTML的时候浏览器会保持前导空白字符，则返回true，目前在IE 6-8中返回false。</p>
						<p><strong>noCloneEvent</strong>: 如果浏览器在克隆元素的时候不会连同事件处理函数一起复制，则返回true，目前在IE中返回false。</p>
						<p><strong>objectAll</strong>: 如果在某个元素对象上执行getElementsByTagName("*")会返回所有子孙元素，则为true，目前在IE 7中为false。</p>
						<p><strong>opacity</strong>: 如果浏览器能适当解释透明度样式属性，则返回true，目前在IE中返回false，因为他用alpha滤镜代替。</p>
						<p><strong>scriptEval</strong>: 使用 appendChild/createTextNode 方法插入脚本代码时，浏览器是否执行脚步，目前在IE中返回false，IE使用 .text 方法插入脚本代码以执行。</p>
						<p><strong>style</strong>: 如果getAttribute("style")返回元素的行内样式，则为true。目前IE中为false，因为他用cssText代替。</p>
						<p><strong>tbody</strong>: 如果浏览器允许table元素不包含tbody元素，则返回true。目前在IE中会返回false，他会自动插入缺失的tbody。</p>
					</div></div>
					<hr />
					<div class="desc"><div>Added in jQuery 1.3 A collection of properties that represent the presence of different browser features or bugs.</div> <div class="longdesc">
jQuery comes with a number of properties included, you should feel free to add your own. Many of these properties are rather low-level so it's doubtful that they'll be useful in general day-to-day development, but mostly used by plugin and core developers.<br />
<br />
The values of all the support properties are determined using feature detection (and do not use any form of browser sniffing). There are some excellent resources that explain how feature detection works:<br />
<br />
http://peter.michaux.ca/articles/feature-detection-state-of-the-art-browser-scripting<br />
http://yura.thinkweb2.com/cft/<br />
http://www.jibbering.com/faq/faq_notes/not_browser_detect.html<br />
The tests included in jQuery.support are as follows:<br />
<br />
boxModel: Is equal to true if the page and browser are rendering according to the W3C CSS Box Model (is currently false in IE 6 and 7 when they are in Quirks Mode). This property is null until document ready occurs.<br />
cssFloat: Is equal to true if style.cssFloat is used to access the current CSS float value (is currently false in IE, it uses styleFloat instead).<br />
hrefNormalized: Is equal to true if the browser leaves intact the results from getAttribute("href")(is currently false in IE, the URLs are normalized).<br />
htmlSerialize: Is equal to true if the browser properly serializes link elements when innerHTML is used (is currently false in IE).<br />
leadingWhitespace: Is equal to true if the browser preserves leading whitespace when innerHTML is used (is currently false in IE 6-8).<br />
noCloneEvent: Is equal to true if the browser does not clone event handlers when elements are cloned (is currently false in IE).<br />
objectAll: Is equal to true if doing getElementsByTagName("*") on an object element returns all descendant elements (is currently false in IE 7).<br />
opacity: Is equal to true if a browser can properly interpret the opacity style property (is currently false in IE, it uses alpha filters instead).<br />
scriptEval: Is equal to true if using appendChild/createTextNode to inject inline scripts executes them (is currently false in IE, it uses .text to insert executable scripts).<br />
style: Is equal to true if getAttribute("style") is able to return the inline style specified by an element (is currently false in IE - it uses cssText instead).<br />
tbody: Is equal to true if the browser allows table elements without tbody elements (is currently false in IE, which automatically inserts tbody if it is not present).
					</div></div>
				</div>
				<h2>返回值</h2>
				<p class="indent">Boolean</p>
				<h2>示例</h2>
				<p class="indent">
					检测浏览器是否支持盒式模型
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					jQuery.support.boxModel
				</div>
			</div>
			<div class="functionitem">jQuery.browser(建议弃用)</div>
			<div class="content">
				<h1>jQuery.browser(建议弃用)</h1>
				<div style="" id="overview" class="tabs-container">
					<div class="desc"><div>浏览器内核标识。依据 navigator.userAgent 判断。</div> <div class="longdesc">可用值: <p> safari </p><p> opera </p><p> msie </p><p> mozilla </p> <p>此属性在 DOM 树加载完成前即有效，可用于为特定浏览器设置 ready 事件。 </p><p>浏览器对象检测技术与此属性共同使用可提供可靠的浏览器检测支持。</p></div></div>
					<hr />
					<div class="desc"><div>Deprecated in jQuery 1.3. Contains flags for the useragent, read from navigator.userAgent.</div> <div class="longdesc">Available flags are: <p> safari </p><p> opera </p><p> msie </p><p> mozilla </p> <p>This property is available before the DOM is ready, therefore you can use it to add ready events only for certain browsers. </p><p>There are situations where object detection is not reliable enough, in such cases it makes sense to use browser detection. </p> <p>A combination of browser and object detection yields quite reliable results.</p></div></div>
				</div>
				<h2>返回值</h2>
				<p class="indent">Map</p>
				<h2>示例</h2>
				<p class="indent">
					在 Microsoft's Internet Explorer 浏览器中返回 true。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.browser.msie 
				</div>
				<hr />
				<p class="indent">
					仅在 Safari 中提示 "this is safari!" 。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					if ($.browser.safari) {<br/>
&nbsp;&nbsp; alert("this is safari!");<br/>
} 
				</div>
			</div>
			<div class="functionitem">jQuery.browser.version(建议弃用)</div>
			<div class="content">
				<h1>jQuery.browser.version(建议弃用)</h1>
				<div style="" id="overview" class="tabs-container">
				<div class="desc"><div>浏览器渲染引擎版本号。</div> <div class="longdesc">典型结果: <p> Internet Explorer: 6.0, 7.0 </p><p> Mozilla/Firefox/Flock/Camino: 1.7.12, 1.8.1.3 </p><p> Opera: 9.20 </p><p> Safari/Webkit: 312.8, 418.9</p></div></div>
				<hr />
				<div class="desc"><div>Deprecated in jQuery 1.3. The version number of the rendering engine for the user's browser.</div> <div class="longdesc">Here are some typical results: <p> Internet Explorer: 6.0, 7.0 </p><p> Mozilla/Firefox/Flock/Camino: 1.7.12, 1.8.1.3 </p><p> Opera: 9.20 </p><p> Safari/Webkit: 312.8, 418.9</p></div></div> </div>
				<h2>返回值</h2>
				<p class="indent">String</p>
				<h2>示例</h2>
				<p class="indent">
					显示当前 IE 浏览器版本号。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					if ( $.browser.msie )<br/>
&nbsp;alert( $.browser.version ); 
				</div>
			</div>
			<div class="functionitem">jQuery.boxModel(建议弃用)</div>
			<div class="content">
				<h1>jQuery.boxModel(建议弃用)</h1>
				<div class="desc">当前页面中浏览器是否使用标准盒模型渲染页面。 建议使用 jQuery.support.boxModel 代替。<a href="http://www.w3.org/TR/REC-CSS2/box.html" class="external text" title="W3C CSS Box Model">W3C CSS 盒模型</a>.</div>
				<hr />
				<div class="desc">Deprecated in jQuery 1.3. States if the current page, in the user's browser, is being rendered using the <a href="http://www.w3.org/TR/REC-CSS2/box.html" class="external text" title="http://www.w3.org/TR/REC-CSS2/box.html">W3C CSS Box Model</a>.</div>
				<h2>返回值</h2>
				<p class="indent">Boolean</p>
				<h2>示例</h2>
				<p class="indent">
					在 Internet Explorer 怪癖模式（QuirksMode）中返回 False。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.boxModel 
				</div>
			</div>
		</div>
		<div class="categoryitem">数组和对象操作</div>
		<div class="category">
			<div class="functionitem">jQuery.each(obj,callback)</div>
			<div class="content">
				<h1>jQuery.each(obj,callback)</h1>
				<div class="desc"><div>通用例遍方法，可用于例遍对象和数组。</div> <div class="longdesc">不同于例遍 jQuery 对象的 $().each() 方法，此方法可用于例遍任何对象。<p>回调函数拥有两个参数：第一个为对象的成员或数组的索引，第二个为对应变量或内容。</p>如果需要退出 each 循环可使回调函数返回 false，其它返回值将被忽略。</div></div>
				<hr />
				<div class="desc"><div>A generic iterator function, which can be used to seamlessly iterate over both objects and arrays.</div> <div class="longdesc">This function is not the same as $().each() - which is used to iterate, exclusively, over a jQuery object. This function can be used to iterate over anything. <p>The callback has two arguments:the key (objects) or index (arrays) as the first, and the value as the second. </p>If you wish to break the each() loop at a particular iteration you can do so by making your function return false. Other return values are ignored.</div></div>
				<h2>返回值</h2>
				<p class="indent">Object</p>
				<h2>参数</h2>
				<p class="indent"><strong>object </strong>(Object) : 需要例遍的对象或数组。</p>
				<p class="indent"><strong>callback </strong>(Function) : (可选) 每个成员/元素执行的回调函数。</p>
				<h2>示例</h2>
				<p class="indent">
					例遍数组，同时使用元素索引和内容。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.each( [0,1,2], function(i, n){<br/>
&nbsp; alert( "Item #" + i + ": " + n );<br/>
});
				</div>
				<hr />
				<p class="indent">
					例遍对象，同时使用成员名称和变量内容。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.each( { name: "John", lang: "JS" }, function(i, n){<br/>
&nbsp; alert( "Name: " + i + ", Value: " + n );<br/>
});
				</div>
			</div>
			<div class="functionitem">jQuery.extend(target,obj1,objN)</div>
			<div class="content">
				<h1>jQuery.extend(target,obj1,[objN])</h1>
				<div class="desc"><div>用一个或多个其他对象来扩展一个对象，返回被扩展的对象。</div> <div class="longdesc">用于简化继承。</div></div>
				<hr />
				<div class="desc"><div>Extend one object with one or more others, returning the original, modified, object.</div> <div class="longdesc">This is a great utility for simple inheritance.</div></div>
				<h2>返回值</h2>
				<p class="indent">Object</p>
				<h2>参数</h2>
				<p class="indent"><strong>target </strong>(Object) : 待修改对象。</p>
				<p class="indent"><strong>object1 </strong>(Object) : 待合并到第一个对象的对象。</p>
				<p class="indent"><strong>objectN </strong>(Object) : (可选) 待合并到第一个对象的对象。</p>
				<h2>示例</h2>
				<p class="indent">
					合并 settings 和 options，修改并返回 settings。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var settings = { validate: false, limit: 5, name: "foo" };<br/>
var options = { validate: true, name: "bar" };<br/>
jQuery.extend(settings, options); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					settings == { validate: true, limit: 5, name: "bar" } 
				</div>
				<hr />
				<p class="indent">
					合并 defaults 和 options, 不修改 defaults。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var empty = {}<br/>
var defaults = { validate: false, limit: 5, name: "foo" };<br/>
var options = { validate: true, name: "bar" };<br/>
var settings = jQuery.extend(empty, defaults, options); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					settings == { validate: true, limit: 5, name: "bar" }<br/>
empty == { validate: true, limit: 5, name: "bar" }
				</div>
			</div>
			<div class="functionitem">jQuery.grep(array,callback,invert)</div>
			<div class="content">
				<h1>jQuery.grep(array,callback,[invert])</h1>
				<div class="desc"><div>使用过滤函数过滤数组元素。</div> <div class="longdesc">此函数至少传递两个参数：待过滤数组和过滤函数。过滤函数必须返回 true 以保留元素或 false 以删除元素。</div></div>
				<hr />
				<div class="desc"><div>Filter items out of an array, by using a filter function.</div> <div class="longdesc">The specified function will be passed two arguments: The current array item and the filter function. The function must return 'true' to keep the item in the array, false to remove it.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array</p>
				<h2>参数</h2>
				<p class="indent"><strong>array </strong>(Array) : 待过滤数组。</p>
				<p class="indent"><strong>callback </strong>(Function) : 此函数将处理数组每个元素。第一个参数为当前元素，第二个参数而元素索引值。此函数应返回一个布尔值。另外，此函数可设置为一个字符串，当设置为字符串时，将视为“lambda-form”（缩写形式？），其中 a 代表数组元素，i 代表元素索引值。如“a &gt; 0”代表“function(a){ return a &gt; 0; }”。</p>
				<p class="indent"><strong>invert </strong>(Boolean) : (可选) 如果 &quot;invert&quot; 为 false 或为设置，则函数返回数组中由过滤函数返回 true 的元素，当&quot;invert&quot; 为 true，则返回过滤函数中返回 false 的元素集。</p>
				<h2>示例</h2>
				<p class="indent">
					过滤数组中小于 0 的元素。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.grep( [0,1,2], function(n,i){<br/>
&nbsp; return n &gt; 0;<br/>
});
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[1, 2] 
				</div>
				<hr />
				<p class="indent">
					排除数组中大于 0 的元素，使用第三个参数进行排除。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.grep( [0,1,2], function(n,i){<br/>
&nbsp; return n &gt; 0;<br/>
}, true); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[0] 
				</div>
			</div>
			<div class="functionitem">jQuery.makeArray(obj)</div>
			<div class="content">
				<h1>jQuery.makeArray(obj)</h1>
				<div class="desc"><div>将类数组对象转换为数组对象。</div> <div class="longdesc">类数组对象有 length 属性，其成员索引为 0 至 length - 1。实际中此函数在 jQuery 中将自动使用而无需特意转换。</div></div>
				<hr />
				<div class="desc"><div>Turns an array-like object into a true array.</div> <div class="longdesc">Array-like objects have a length property and its properties are numbered from 0 to length - 1. Typically it will be unnecessary to use this function if you are using jQuery which uses this function internally.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array</p>
				<h2>参数</h2>
				<p class="indent"><strong>obj </strong>(Object) : 类数组对象。</p>
				<h2>示例</h2>
				<p class="indent">
					过滤数组中小于 0 的元素。 
				</p>
				<p class="indent"><strong>HTML 代码:</strong></p>
				<div class="code">
&lt;div&gt;First&lt;/div&gt;&lt;div&gt;Second&lt;/div&gt;&lt;div&gt;Third&lt;/div&gt;&lt;div&gt;Fourth&lt;/div&gt;
				</div>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
var arr = jQuery.makeArray(document.getElementsByTagName("div"));<br/>
arr.reverse(); // 使用数组翻转函数
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
Fourth<br/>
Third<br/>
Second<br/>
First
				</div>
			</div>
			<div class="functionitem">jQuery.map(array,callback)</div>
			<div class="content">
				<h1>jQuery.map(array,callback)</h1>
				<div class="desc"><div>将一个数组中的元素转换到另一个数组中。</div> <div class="longdesc">作为参数的转换函数会为每个数组元素调用，而且会给这个转换函数传递一个表示被转换的元素作为参数。转换函数可以返回转换后的值、null（删除数组中的项目）或一个包含值的数组，并扩展至原始数组中。</div></div>
				<hr />
				<div class="desc"><div>Translate all items in an array to another array of items.</div> <div class="longdesc">The translation function that is provided to this method is called for each item in the array and is passed one argument: The item to be translated. The function can then return the translated value, 'null' (to remove the item), or an array of values - which will be flattened into the full array.</div></div>
				<h2>返回值</h2>
				<p class="indent">Array</p>
				<h2>参数</h2>
				<p class="indent"><strong>array </strong>(Array) : 待转换数组。</p>
				<p class="indent"><strong>callback </strong>(Function) : 为每个数组元素调用，而且会给这个转换函数传递一个表示被转换的元素作为参数。函数可返回任何值。另外，此函数可设置为一个字符串，当设置为字符串时，将视为“lambda-form”（缩写形式？），其中 a 代表数组元素。如“a * a”代表“function(a){ return a * a; }”。</p>
				<h2>示例</h2>
				<p class="indent">
					将原数组中每个元素加 4 转换为一个新数组。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.map( [0,1,2], function(n){<br/>
&nbsp; return n + 4;<br/>
});
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[4, 5, 6] 
				</div>
				<hr />
				<p class="indent">
					原数组中大于 0 的元素加 1 ，否则删除。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.map( [0,1,2], function(n){<br/>
&nbsp; return n &gt; 0 ? n + 1 : null;<br/>
});
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[2, 3] 
				</div>
				<hr />
				<p class="indent">
					原数组中每个元素扩展为一个包含其本身和其值加 1 的数组，并转换为一个新数组。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.map( [0,1,2], function(n){<br/>
&nbsp; return [ n, n + 1 ];<br/>
});
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[0, 1, 1, 2, 2, 3] 
				</div>
			</div>
			<div class="functionitem">jQuery.inArray(value,array)</div>
			<div class="content">
				<h1>jQuery.inArray(value,array)</h1>
				<div class="desc">确定第一个参数在数组中的位置(如果没有找到则返回 -1 )。</div><hr />
				<div class="desc">Determine the index of the first parameter in the Array (-1 if not found).</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>value </strong>(Any) : 用于在数组中查找是否存在</p>
				<p class="indent"><strong>array </strong>(Array) : 待处理数组。</p>
				<h2>示例</h2>
				<p class="indent">
					删除重复 div 标签。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					var arr = [ 4, "Pete", 8, "John" ];<br />
					jQuery.inArray("John", arr);&nbsp;&nbsp;//3<br />
					jQuery.inArray(4, arr);&nbsp;&nbsp;//0<br />
					jQuery.inArray("David", arr);&nbsp;&nbsp;//-1<br />
				</div>
			</div>
			<div class="functionitem">jQuery.unique(array)</div>
			<div class="content">
				<h1>jQuery.unique(array)</h1>
				<div class="desc">删除数组中重复元素。</div><hr />
				<div class="desc">Remove all duplicate elements from an array of elements.</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>array </strong>(Array) : 待处理数组。</p>
				<h2>示例</h2>
				<p class="indent">
					删除重复 div 标签。
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.unique(document.getElementsByTagName("div")); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[&lt;div&gt;, &lt;div&gt;, ...]
				</div>
			</div>
		</div>
		<div class="categoryitem">测试操作</div>            
		<div class="category">
			<div class="functionitem">jQuery.isArray( obj )</div>
			<div class="content">
				<h1>jQuery.isArray(obj)</h1>
				<div class="desc"><div>jQuery 1.3 新增。测试对象是否为数组。</div><hr />
				<div>Determine if the parameter passed is a array.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Boolean</p>
				<h2>参数</h2>
				<p class="indent"><strong>obj </strong>(Object) : 用于测试是否为数组的对象</p>
				<h2>示例</h2>
				<p class="indent">
					检测是否为数组
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$("b").append( "" + $.isArray([]) );
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					&lt;b&gt;true&lt;/b&gt;
				</div>
			</div>
			<div class="functionitem">jQuery.isFunction(obj)</div>
			<div class="content">
				<h1>jQuery.isFunction(obj)</h1>
				<div class="desc"><div>测试对象是否为函数。</div><hr />
				<div>Determine if the parameter passed is a function.</div></div>  
				<h2>返回值</h2>
				<p class="indent">Boolean</p>
				<h2>参数</h2>
				<p class="indent"><strong>obj </strong>(Object) : 用于测试是否为函数的对象</p>
				<h2>示例</h2>
				<p class="indent">
					检测是否为函数
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
<pre>    function stub() {
    }
	var objs = [
            function () {},
            { x:15, y:20 },
            null,
            stub,
            &quot;function&quot;
          ];
        jQuery.each(objs, function (i) {
        var isFunc = <strong>jQuery.isFunction</strong>(objs[i]);
        $(&quot;span:eq( &quot; + i + &quot;)&quot;).text(isFunc);
      });  </pre>
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					[ true,false,false,true,false ]
				</div>
			</div>
		</div>
		<div class="categoryitem">字符串操作</div>
		<div class="category">
			<div class="functionitem">jQuery.trim(str)</div>
			<div class="content">
				<h1>jQuery.trim(str)</h1>
				<div class="desc"><div>去掉字符串起始和结尾的空格。</div><hr />
				<div>Remove the whitespace from the beginning and end of a string.</div>
				</div>
				<h2>返回值</h2>
				<p class="indent">jQuery</p>
				<h2>参数</h2>
				<p class="indent"><strong>str </strong>(String) : 需要处理的字符串</p>
				<h2>示例</h2>
				<p class="indent">
					去掉字符串起始和结尾的空格。 
				</p>
				<p class="indent"><strong>jQuery 代码:</strong></p>
				<div class="code">
					$.trim("  hello, how are you?  "); 
				</div>
				<p class="indent"><strong>结果:</strong></p>
				<div class="code">
					"hello, how are you?"
				</div>
			</div>
		</div>
	</div>
	<!-- Abouts -->
	<div class="menuitem">关于</div>
	<div class="functionmenu">
		<div class="categoryitem">关于翻译</div>
		<div class="category">
			<div class="functionitem">关于jQuery 1.3 版翻译</div>
			<div class="content">
				<h1>关于jQuery 1.3 版翻译</h1>
				<div class="desc">jQuery 1.3自从2008年1月14日发布后，后引来了各界的关注。我们也随即投入到翻译文档的工作中来。经过4天的努力，终于完工了。这个版本更新了不少东西，具体还请看<a href="changelog.txt">changelog</a>。感谢Cloudream的热情帮忙。还要感谢一揪制作chm版。这个版本还加入了检查更新的功能。如果有需要的同学可轻松查看是否有更新的中文文档(chm版中的检查更新也将同步升级)。
				</div>
			</div>
			<div class="functionitem">关于jQuery 1.2 版翻译</div>
			<div class="content">
				<h1>关于jQuery 1.2 版翻译</h1>
				<div class="desc"><div><p>首先感谢 <a href="http://www.cn-cuckoo.com/">为之漫笔</a> 。他是1.1API的翻译者。1.2的翻译是完全基于1.1的API翻译的，拜一记。本次翻译临近结束时，由他翻译的Learning jQuery 的中文版《jQuery基础教程》即将出版。作为国内jQuery的引路人的他，我由衷地向他表示感谢！</p><p>其次感谢 <a href="http://mrwlwan.wordpress.com/">Ross Wan</a> 写的这个仿Visiul jQuery的样式。本次翻译是基于他的英文版制作的。(由于GFW缘故，其博客请通过代理访问)</p><p>最后感谢 <a href="http://www.cloudream.name/">Cloudream</a>,他也一起参与翻译了工具、原Dimensions插件和AJAX部分。并且加入了英文说明切换功能。</p><p>断断续续翻译这个API有段时间了，虽然大部分都基于1.1的API复制过来，但也得校对以及跟官网的文档进行比较。所以也花了点时间。</p><p>同时欢迎利用此版制作其他发行版以方便广大jQuery爱好者。转载请保留版权信息，谢谢。</p></div>
				</div>
			</div>
		</div>
		<div class="categoryitem">提交bug及获取更新</div>
		<div class="category">
			<div class="functionitem">提交bug及获取更新</div>
			<div class="content">
				<h1>提交bug以及获取更新</h1>
				<div class="desc">
					如果大家使用过程中发现了什么翻译错误，可以找到项目地址 <br /><a href="http://code.google.com/p/jquery-api-zh-cn/issues/list">http://code.google.com/p/jquery-api-zh-cn/issues/list</a><br />来反馈。
				</div>
				<h2>版本信息</h2>
				<div class="indent">
					<p> $Rev: 121 $ <br /> $Author: Shawphy $ <br /> $Date: 2009-01-18 23:43:02 +0800 (星期日, 2009-01-18) $</p><p>changelog: <a href="changelog.txt">changelog.txt</a></p>
				</div>
				<h2>检查更新</h2>
				<div>
					<button id="checkUpdate">检查更新</button>
					<div id="update"></div>
					<script type="text/javascript">
					<!--
						var curVer="090119";
						$("#checkUpdate").click(function(){
							$('<script src="http://jquery-api-zh-cn.googlecode.com/svn/trunk/update.js" type="text/javascript"><script>').appendTo("head")
						});
					//-->
					</script>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
var pageTracker = _gat._getTracker("UA-5320749-1");
pageTracker._initData();
pageTracker._trackPageview();
</script>
</body>
</html>
   
i n d e x . h t m l    $7 a 3 3 0 f 8 3 - 8 d 2 7 - 4 d 1 6 - 9 c 2 b - 1 c a d e e 9 0 8 0 5 e    $3 7 e 3 d 1 c c - 7 b 6 0 - 4 6 e 0 - b 7 4 d - d 1 6 d 9 1 c c d f c 3 