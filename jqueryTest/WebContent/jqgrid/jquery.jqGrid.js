/*
 * jqGrid  3.0 - jQuery Grid
 * Copyright (c) 2008, Tony Tomov, tony@trirand.com
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 * Date: 2008-03-22 rev 32
 */

// we make it simple as possible
function jqGridInclude()
{
    var pathtojsfiles = "jqgrid/"; // need to be ajusted
    // if you do not want some module to be included
    // set include to false.
    // by default all modules are included.
    var minver = true;
    var modules = [
        { include: true, incfile:'js/grid.base.js',minfile: 'min/grid.base-min.js'}, // jqGrid base
        { include: true, incfile:'js/grid.formedit.js',minfile: 'min/grid.formedit-min.js' }, // jqGrid Form editing
        { include: true, incfile:'js/grid.inlinedit.js',minfile: 'min/grid.inlinedit-min.js' }, // jqGrid inline editing
        { include: true, incfile:'js/grid.subgrid.js',minfile: 'min/grid.subgrid-min.js'} //jqGrid subgrid
    ];
    for(var i=0;i<modules.length; i++)
    {
        if(modules[i].include == true) {
        	if (minver != true) IncludeJavaScript(pathtojsfiles+modules[i].incfile);
        	else IncludeJavaScript(pathtojsfiles+modules[i].minfile);
        }
    }
    function IncludeJavaScript(jsFile)
    {
      document.write('<script type="text/javascript" src="'
        + jsFile + '"></script>'); 
    }
}

jqGridInclude();