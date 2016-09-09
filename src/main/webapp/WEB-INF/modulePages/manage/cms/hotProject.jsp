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
        
        .title {
            border-width:1px 1px 0 1px;
            border-style:solid;
            border-color:#aaa;
            height:31px;
            line-height:31px;
            text-align:center;
            font-size: 16px;
            font-weight: bold;
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
            width: 200px;
        }
        
        .img-line {
            height:279px;
        }
        
        .img-box {
            width: 260px;
            height:260px;
            line-height:200px;
            font-size:30px;
            color:#aaa;
        }
        
        .dlg-summary-line {
            height:124px;
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
            <table class="dlg-body" style="table-layout: fixed;">
            <caption class="title">${euler:i18n('hotProject')}</caption>
            <tr class="dlg-line">
            <td colspan="2" class="dlg-label-span">${euler:i18n('hotProject')}${euler:i18n('global.one')}</td>
            <td colspan="2" class="dlg-label-span">${euler:i18n('hotProject')}${euler:i18n('global.two')}</td>
            <td colspan="2" class="dlg-label-span">${euler:i18n('hotProject')}${euler:i18n('global.three')}</td>
            </tr>
            <tr class="dlg-line img-line">
                <td colspan="2" class="dlg-input-span">
                    <img class="dlg-input img-box" id="dlg_img-show1" src="" alt="${euler:i18n('jsp.hotProject.noImg')}">
                </td>
                <td colspan="2" class="dlg-input-span">
                    <img class="dlg-input img-box" id="dlg_img-show2" src="" alt="${euler:i18n('jsp.hotProject.noImg')}">
                </td>
                <td colspan="2" class="dlg-input-span">
                    <img class="dlg-input img-box" id="dlg_img-show3" src="" alt="${euler:i18n('jsp.hotProject.noImg')}">
                </td>            
            </tr>
            <tr class="dlg-line">
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.newImg')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img1" name="img1">
                </td>
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.newImg')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img2" name="img2">
                </td>
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.newImg')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img3" name="img3">
                </td>
            </tr>
            <tr class="dlg-line">
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.name')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-textbox dlg-input" data-options="" id="dlg_name1" name="name1">
                </td>
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.name')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-textbox dlg-input" data-options="" id="dlg_name2" name="name2">
                </td>
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.name')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-textbox dlg-input" data-options="" id="dlg_name3" name="name3">
                </td>
            </tr>
            <tr class="dlg-line  dlg-summary-line">
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.summary')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-textbox dlg-input" style="height:118px;" data-options="multiline:true" id="dlg_summary1" name="summary1">
                </td>
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.summary')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-textbox dlg-input" style="height:118px;" data-options="multiline:true" id="dlg_summary2" name="summary2">
                </td>
                <td class="dlg-label-span">
                    <label class="dlg-label">${euler:i18n('hotProject.summary')}</label>
                </td>
                <td class="dlg-input-span">
                    <input class="easyui-textbox dlg-input" style="height:118px;" data-options="multiline:true" id="dlg_summary3" name="summary3">
                </td>
            </tr>
            </table>
        </form>
    </div>
    <%@ include file="/WEB-INF/commonPages/easyui-js.jsp"%>
    <script>    
        $(function(){
            loadImgs();
        });
        
        function onSave() {
            $('#fm').form('submit', {
                url:'saveHotProject',
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
                url:'loadHotProject',
                type:'GET',
                async:true,
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    $.messager.alert("${euler:i18n('global.error')}", XMLHttpRequest.responseText);
                },
                success:function(data, textStatus) {
                    var upload = '${contextPath}/upload/';
                    
                    if(data[0].imgFileName != "")
                        setImgSrc('#dlg_img-show1', 260, 260, upload+data[0].imgFileName);  
                    $('#dlg_name1').textbox('setValue', data[0].name);  
                    $('#dlg_summary1').textbox('setValue', data[0].summary);  
                    
                    if(data[1].imgFileName != "")
                        setImgSrc('#dlg_img-show2', 260, 260, upload+data[1].imgFileName); 
                        //$('#dlg_img-show2').attr('src', upload+data.hotProjects[1].imgFileName);  
                    $('#dlg_name2').textbox('setValue', data[1].name);  
                    $('#dlg_summary2').textbox('setValue', data[1].summary);  
                    
                    if(data[2].imgFileName != "")
                        setImgSrc('#dlg_img-show3', 260, 260, upload+data[2].imgFileName); 
                        //$('#dlg_img-show3').attr('src', upload+data.hotProjects[2].imgFileName);  
                    $('#dlg_name3').textbox('setValue', data[2].name);  
                    $('#dlg_summary3').textbox('setValue', data[2].summary);  
                }
            });
        }
        
        function onCancel() {
            loadImgs();
        }
    </script>
    
</body>

</html>