<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <%@ include file="/WEB-INF/commonPages/easyui-css.jsp"%>

    <title></title>
    
    <style>
        .dlg-window {
            background-color:#E0ECFF;
            padding:6px; 
        }
        
        .dlg-body {
            border-collapse:collapse;
        }
        
        .dlg-body-title {
            border-width:1px 1px 0 1px;
            border-style:solid;
            border-color:#aaa;
        }
        
        .dlg-line {
        }
        
        .dlg-label-span {
            border:1px solid #aaa;
            text-align:center;
        }
        
        .dlg-input-span {
            border:1px solid #aaa;
        }
        
        .dlg-label {
            width: 60px;
        }
        
        .dlg-input {
            width: 800px;
        }
        
        .img-line {
            height:310px;
        }
        
        .img-box {
            height:304px;
            line-height:304px;
            font-size:30px;
            color:#aaa;
        }
    </style>

</head>

<body">
    <div id="dlg" class="easyui-dialog dlg-window" style="width:100%;"
                    data-options="
                        closed:false,
                        iconCls:'icon-save',
                        resizable:false,
                        modal:true,
                        constrain:true,
                        top:0,
                        maximized:true,
                        noheader:true,
                        border:false,
                        buttons:[{text:'${euler:i18n('global.save')}', iconCls:'icon-ok', handler:onSave},{text:'${euler:i18n('global.cancel')}', iconCls:'icon-cancel', handler:onCancel}]">
        <form id="fm" class="dlg-form" enctype="multipart/form-data" method="post">
            <div class="dlg-body">
            <div class="dlg-body-title">${euler:i18n('slideshow.img')}${euler:i18n('global.one')}</div>
            <div class="dlg-line img-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('global.priview')}</label></span><span class="dlg-input-span"><img class="dlg-input img-box" id="dlg_img-show1" src="" alt="${euler:i18n('jsp.slideshow.noImg')}"></span></div>
            <div class="dlg-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('slideshow.newImg')}</label></span><span class="dlg-input-span"><input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img1" name="img1"></span></div>
            <div class="dlg-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('slideshow.url')}</label></span><span class="dlg-input-span"><input class="easyui-textbox dlg-input" data-options="" id="dlg_url1" name="url1"></span></div>
            </div>
            <br><br><br>
            <div class="dlg-body">
            <div class="dlg-body-title">${euler:i18n('slideshow.img')}${euler:i18n('global.two')}</div>
            <div class="dlg-line img-line""><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('global.priview')}</label></span><span class="dlg-input-span"><img class="dlg-input img-box" id="dlg_img-show2" src="" alt="${euler:i18n('jsp.slideshow.noImg')}"></span></div>
            <div class="dlg-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('slideshow.newImg')}</label></span><span class="dlg-input-span"><input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img2" name="img2"></span></div>
            <div class="dlg-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('slideshow.url')}</label></span><span class="dlg-input-span"><input class="easyui-textbox dlg-input" data-options="" id="dlg_url2" name="url2"></span></div>
            </div>
            <br><br><br>
            <div class="dlg-body">
            <div class="dlg-body-title">${euler:i18n('slideshow.img')}${euler:i18n('global.three')}</div>
            <div class="dlg-line img-line""><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('global.priview')}</label></span><span class="dlg-input-span"><img class="dlg-input img-box" id="dlg_img-show3" src="" alt="${euler:i18n('jsp.slideshow.noImg')}"></span></div>
            <div class="dlg-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('slideshow.newImg')}</label></span><span class="dlg-input-span"><input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img3" name="img3"></span></div>
            <div class="dlg-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('slideshow.url')}</label></span><span class="dlg-input-span"><input class="easyui-textbox dlg-input" data-options="" id="dlg_url3" name="url3"></span></div>
            </div>
            <br><br><br>
            <div class="dlg-body">
            <div class="dlg-body-title">${euler:i18n('slideshow.img')}${euler:i18n('global.four')}</div>
            <div class="dlg-line img-line""><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('global.priview')}</label></span><span class="dlg-input-span"><img class="dlg-input img-box" id="dlg_img-show4" src="" alt="${euler:i18n('jsp.slideshow.noImg')}"></span></div>
            <div class="dlg-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('slideshow.newImg')}</label></span><span class="dlg-input-span"><input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img4" name="img4"></span></div>
            <div class="dlg-line"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('slideshow.url')}</label></span><span class="dlg-input-span"><input class="easyui-textbox dlg-input" data-options="" id="dlg_url4" name="url4"></span></div>
            </div>
        </form>
    </div>
    <%@ include file="/WEB-INF/commonPages/easyui-js.jsp"%>
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
                    
                    if(data[0].imgFileName != "")
                        setImgSrc('#dlg_img-show1', 800, 300, upload+data[0].imgFileName);  
                    $('#dlg_url1').textbox('setValue', data[0].url);  
                    
                    if(data[1].imgFileName != "")
                        setImgSrc('#dlg_img-show2', 800, 300, upload+data[1].imgFileName); 
                        //$('#dlg_img-show2').attr('src', upload+data.slideshows[1].imgFileName);  
                    $('#dlg_url2').textbox('setValue', data[1].url);
                    
                    if(data[2].imgFileName != "")
                        setImgSrc('#dlg_img-show3', 800, 300, upload+data[2].imgFileName); 
                        //$('#dlg_img-show3').attr('src', upload+data.slideshows[2].imgFileName);  
                    $('#dlg_url3').textbox('setValue', data[2].url);

                    if(data[3].imgFileName != "")
                        setImgSrc('#dlg_img-show4', 800, 300, upload+data[3].imgFileName); 
                        //$('#dlg_img-show4').attr('src', upload+data.slideshows[3].imgFileName);  
                    $('#dlg_url4').textbox('setValue', data[3].url);
                }
            });
        }
        
        function onCancel() {
            loadImgs();
        }
    </script>
    
</body>

</html>