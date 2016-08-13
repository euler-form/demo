<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <%@ include file="/WEB-INF/commonPages/css.jsp"%>

    <title></title>
    
    <style>
        .dlg-form {
            padding:6px;
        }
        
        .dlg-label {
            width: 60px;
        }
        
        .dlg-input {
            width: 800px;
        }
    </style>

</head>
<!--  draggable:false,
                        closable:false,
                        closed:false,
                        resizable:false,
                        modal:true,
                        noheader:true,
                        maximized:true,  style="width:100%;"-->
<body">
    <div id="dlg" class="easyui-dialog dlg-window" style="width:100%;"
                    data-options="
                        zIndex:99999999,
                        closed:false,
                        iconCls:'icon-save',
                        resizable:false,
                        modal:true,
                        constrain:true,
                        top:0,
                        maximized:true,
                        noheader:true,
                        buttons:[{text:'${euler:i18n('global.save')}', iconCls:'icon-ok', handler:onSave},{text:'${euler:i18n('global.cancel')}', iconCls:'icon-cancel', handler:onCancel}]">
        <form id="fm" class="dlg-form" enctype="multipart/form-data" method="post">
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.img')}1</label><span class="dlg-span"><img class="dlg-input" id="dlg_img-show1" src=""></span></div>
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.newImg')}</label><span class="dlg-span"><input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img1" name="img1"></span></div>
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.url')}</label><span class="dlg-span"><input class="easyui-textbox dlg-input" data-options="" id="dlg_url1" name="url1"></span></div>
            <br><br><br>
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.img')}2</label><span class="dlg-span"><img class="dlg-input" id="dlg_img-show2" src=""></span></div>
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.newImg')}</label><span class="dlg-span"><input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img2" name="img2"></span></div>
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.url')}</label><span class="dlg-span"><input class="easyui-textbox dlg-input" data-options="" id="dlg_url2" name="url2"></span></div>
            <br><br><br>
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.img')}3</label><span class="dlg-span"><img class="dlg-input" id="dlg_img-show3" src=""></span></div>
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.newImg')}</label><span class="dlg-span"><input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img3" name="img3"></span></div>
            <div class="dlg-line"><label class="dlg-label">${euler:i18n('slideshow.url')}</label><span class="dlg-span"><input class="easyui-textbox dlg-input" data-options="" id="dlg_url3" name="url3"></span></div>
        </form>
    </div>
    <script src="${contextPath}/resources/scripts/lib/easyui/jquery.min.js"></script>
    <script src="${contextPath}/resources/scripts/lib/easyui/jquery.easyui.min.js"></script>
    <script src="${contextPath}/resources/scripts/lib/easyui/easyui-lang-zh_CN.js"></script>
    <script src="${contextPath}/resources/scripts/lib/common-dict.js"></script>
    <script src="${contextPath}/resources/scripts/lib/util.js"></script>
    <script>    
        $(function(){
            loadImgs();
        });
        
        function onSave() {
            $('#fm').form('submit', {
                url:'saveSlideshow',
                onSumit:function(){
                    return $(this).form('validate');
                },
                success:function(data) {
                    if(data) {
                        $.messager.alert("${euler:i18n('global.error')}", data);
                        return;
                    }
                    loadImgs();
                }
            });
        }
        
        function loadImgs() {
            $.ajax({
                url:'loadSlideshow',
                type:'GET',
                async:true,
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    $.messager.alert("${euler:i18n('global.error')}", XMLHttpRequest.responseText);
                },
                success:function(data, textStatus) {
                    var upload = '${contextPath}/upload/';
                    $('#dlg_img-show1').attr('src', upload+data.slideshows[0].imgFileName);  
                    $('#dlg_url1').textbox('setValue', data.slideshows[0].url);  
                    $('#dlg_img-show2').attr('src', upload+data.slideshows[1].imgFileName);  
                    $('#dlg_url2').textbox('setValue', data.slideshows[1].url);      
                    $('#dlg_img-show3').attr('src', upload+data.slideshows[2].imgFileName);  
                    $('#dlg_url3').textbox('setValue', data.slideshows[2].url);                                 
                }
            });
        }
        
        function onCancel() {
            loadImgs();
        }
    </script>
    
</body>

</html>