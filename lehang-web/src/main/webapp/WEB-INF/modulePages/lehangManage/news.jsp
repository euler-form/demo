<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <%@ include file="/WEB-INF/commonPages/easyui-css.jsp"%>
    <script src="${contextPath}/resources/scripts/lib/ueditor/ueditor.config.js"></script>
    <script src="${contextPath}/resources/scripts/lib/ueditor/ueditor.all.min.js"> </script>
    <script src="${contextPath}/resources/scripts/lib/ueditor/lang/zh-cn/zh-cn.js"></script>

    <title></title>
    
    <style>
        .dlg-label {
            width: 48px;
        }
        .dlg-input{
            width: 800px;
        }
    </style>

</head>

<body class="easyui-layout">

    <div data-options="region:'north'" style="overflow:hidden;">
        <form id="search-form">
            <table class="search-table">
                <tr>
                    <td>${euler:i18n('news.title')}</td>
                    <td><input class="easyui-textbox search-input" id="query_name" name="query.title"></td>
                    <td>${euler:i18n('news.pubDate')}</td>
                    <td><input class="easyui-datebox search-input" id="query_pubDate" name="query.pubDate"></td>
                </tr>
            </table>
            <table class="search-btn-table">
                <tr><td>
                <a class="easyui-linkbutton search-btn" data-options="iconCls:'icon-search'" id="search-btn" onclick="onSearch()">${euler:i18n('global.search')}</a>
                <a class="easyui-linkbutton search-btn" data-options="iconCls:'icon-reload'" id="reset-btn" onclick="onReset()">${euler:i18n('global.reset')}</a>
                </td></tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center'" style="background:#eee;">
        
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="onAdd()">${euler:i18n('global.add')}</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" id="editBtn" iconCls="icon-edit" plain="true" onclick="onEdit()">${euler:i18n('global.edit')}</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="onDelete()">${euler:i18n('global.delete')}</a>
        </div>
        <table id="dg" class="easyui-datagrid" 
            data-options="
                fit:true,
                url:'findNewsByPage',
                toolbar:'#toolbar',
                fitColumns:false,
                rownumbers:true,
                remoteSort:false,
                pagination:true,
                singleSelect:false,
                onDblClickRow:onDblClickRow">
            <thead>
                <tr>
                    <th data-options="field:'ck', checkbox:true"></th>
                    <th data-options="field:'title',align:'center',width:'200px'">${euler:i18n('news.title')}</th>
                    <th data-options="field:'pubDate',align:'center',width:'200px',formatter:unixDateFormatter">${euler:i18n('news.pubDate')}</th>
                    <th data-options="field:'id',align:'center',width:'200px',formatter:newsPriviewFormatter">${euler:i18n('jsp.news.priview')}</th>
                </tr>
            </thead>
        </table>
        <div id="dlg" class="easyui-dialog dlg-window" style="height:90%;width:90%;"
                data-options="
                    closed:true,
                    iconCls:'icon-save',
                    resizable:false,
                    modal:false,
                    onClose:clearDlg,
                    constrain:true,
                    buttons:[{text:'${euler:i18n('global.save')}', iconCls:'icon-ok', handler:onSave},{text:'${euler:i18n('global.cancel')}', iconCls:'icon-cancel', handler:onCancel}]">
            <form id="fm" class="dlg-form" enctype="multipart/form-data" method="post">
                <input type="hidden" id="dlg_id" name="id">
                <div class="dlg-line"><span class="dlg-label-span"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('news.title')}</label></span><span class="dlg-input-span"><input class="easyui-textbox dlg-input" data-options="required:true" id="dlg_title" name="title"></span></div>
                <div class="dlg-line"><span class="dlg-label-span"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('news.img')}</label></span><span class="dlg-input-span"><img class="dlg-input img-box" id="dlg_img-show1" src="" alt="${euler:i18n('jsp.news.noImg')}"></span></div>
                <div class="dlg-line"><span class="dlg-label-span"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('jsp.news.uploadImg')}</label></span><span class="dlg-input-span"><input class="easyui-filebox dlg-input" data-options="buttonText:'${euler:i18n('global.chooseFile')}'" id="dlg_img" name="img"></span></div>
                <div class="dlg-line"><span class="dlg-label-span"><span class="dlg-label-span"><label class="dlg-label">${euler:i18n('news.text')}</label></span><span class="dlg-input-span"><script class="dlg-input" id="editor" type="text/plain" style="height:240px;margin:2px;width:800px;"></script></span></div>
            </form>
        </div>        
    </div>
    <%@ include file="/WEB-INF/commonPages/easyui-js.jsp"%>
    
    <script>
    var ue = UE.getEditor(
            'editor',
            {
                toolbars: [
                    [
                        'source', //源代码
                        //'preview', //预览
                        'print', //打印
                        '|',
                        'undo', //撤销
                        'redo', //重做
                        '|',
                        'formatmatch', //格式刷
                        'horizontal', //分隔线
                        'searchreplace', //查询替换
                        '|',
                        'forecolor', //字体颜色
                        'backcolor', //背景色
                        '|',
                        'justifyleft', //居左对齐
                        'justifyright', //居右对齐
                        'justifycenter', //居中对齐
                        'justifyjustify', //两端对齐
                        '|',
                        'link', //超链接
                        'unlink', //取消链接
                        'simpleupload', //单图上传
                        'map', //Baidu地图
                        'attachment', //附件
                    ],
                    [
                        'paragraph', //段落格式
                        'fontfamily', //字体
                        'fontsize', //字号
                        '|',
                        'bold', //加粗
                        'italic', //斜体
                        'underline', //下划线
                        'strikethrough', //删除线
                        'subscript', //下标
                        'superscript', //上标
                        'fontborder', //字符边框
                        '|',
                        'forecolor', //字体颜色
                        'backcolor' //背景色
                    ],
                    [
                        'inserttable', //插入表格
                        'insertrow', //前插入行
                        'insertcol', //前插入列
                        'deleterow', //删除行
                        'deletecol', //删除列
                        'mergeright', //右合并单元格
                        'mergedown', //下合并单元格
                        'mergecells', //合并多个单元格
                        'splittorows', //拆分成行
                        'splittocols', //拆分成列
                        'splittocells', //完全拆分单元格
                        'inserttitle', //插入标题
                        'deletecaption', //删除表格标题
                        'deletetable', //删除表格
                        'insertparagraphbeforetable',//"表格前插入行"
                        'edittable', //表格属性
                        'edittd' //单元格属性
                    ]
                ]
            });
    
    
        $(function(){
        });
        
        function refreshDatagrid(){
            var jsonParam = $('#search-form').serializeJson();
            $('#dg').datagrid('reload', jsonParam);
        }
        
        function onSearch() {
            var jsonParam = $('#search-form').serializeJson();
            $('#dg').datagrid('load', jsonParam);            
        }
        
        function onReset() {
            $('#search-form').form('clear');
        }
        
        function onAdd() {
            $('#fm').form('clear');
            $('#dlg').dialog('open').dialog('setTitle', "${euler:i18n('jsp.news.addNews')}");
        }
        
        function onEdit() {
            var row = $('#dg').datagrid('getSelections');
            
            if(row == null || row.length < 1){
                $.messager.alert("${euler:i18n('global.remind')}", "${euler:i18n('global.pleaseSelectRowsToEdit')}");
            } else if(row){
                $('#fm').form('load', row[0]);
                $('#dlg').dialog('open').dialog('setTitle', "${euler:i18n('jsp.news.editNews')}");
                
            }
        }
        
        function onSave() {
            $('#fm').form('submit', {
                url:'saveNews',
                onSumit:function(){
                    return $(this).form('validate');
                },
                success:function(data) {
                    if(data) {
                        $.messager.alert("${euler:i18n('global.error')}", data);
                        return;
                    }
                    clearDlg();
                    onClose();
                    refreshDatagrid();
                }
            });
        }
        
        function onDelete() {
            var row = $('#dg').datagrid('getSelections');
            
            if(row == null || row.length < 1){
                $.messager.alert("${euler:i18n('global.remind')}", "${euler:i18n('global.pleaseSelectRowsToDelete')}");
            } else if(row){
                $.messager.confirm("${euler:i18n('global.warn')}", "${euler:i18n('global.sureToDelete')}", function(r) {
                    if(r) {
                        var ids = "";
                        for(var i = 0; i < row.length; i++){
                            ids += row[i].id + ';';
                        }
                        $.ajax({
                            url:'deleteNewss',
                            type:'POST',
                            async:true,
                            data: "ids=" + ids,
                            error:function(XMLHttpRequest, textStatus, errorThrown) {
                                $.messager.alert("${euler:i18n('global.error')}", XMLHttpRequest.responseText);
                            },
                            success:function(data, textStatus) {
                                refreshDatagrid();                                    
                            }
                        });
                    }
                });
            }
        }
        
        function onCancel() {
            clearDlg();
            onClose();
        }
        
        function clearDlg(){
            $('#fm').form('clear');            
        }
        
        function onClose(){
            $('#dlg').dialog('close');
        }
        
        function onDblClickRow(rowIndex, rowData){
            var row = $('#dg').datagrid('clearSelections');
            var row = $('#dg').datagrid('selectRow', rowIndex);
            onEdit();
        }
        
        function newsPriviewFormatter(value, row, index) {
            return '<a href="javascript:void(0)" onClick="onPriview(\''+value+'\')">${euler:i18n('jsp.news.priview')}</a>';
        }
        
        function onPriview(id) {
            alert(id);
        }
    </script>
</body>

</html>