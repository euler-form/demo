<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <%@ include file="/WEB-INF/commonPages/easyui-css.jsp"%>

    <title></title>
    
    <style>
        .dlg-input{
            width: 200px;
        }
    </style>

</head>

<body class="easyui-layout">

    <div data-options="region:'north'" style="overflow:hidden;">
        <form id="search-form">
            <table class="search-table">
                <tr>
                    <td>${euler:i18n('position.name')}</td>
                    <td><input class="easyui-textbox search-input" id="query_name" name="query.name"></td>
                    <td>${euler:i18n('position.summary')}</td>
                    <td colspan="3"><input class="easyui-textbox search-input" style="width:100%" id="query_summary" name="query.summary"></td>
                </tr>
                <tr>
                    <td>${euler:i18n('position.companyName')}</td>
                    <td><input class="easyui-combobox search-input" id="query_companyId" name="query.companyId"
                            data-options="panelHeight:'auto',panelMaxHeight:'200px'"></td>
                    <td>${euler:i18n('position.acco')}</td>
                    <td><input class="easyui-combobox search-input" id="query_acco" name="query.acco"
                            data-options="panelHeight:'auto',panelMaxHeight:'200px'"></td>
                    <td>${euler:i18n('position.acType')}</td>
                    <td><input class="easyui-textbox search-input" id="query_acType" name="query.acType"></td>
                </tr>
                <tr>
                    <td>${euler:i18n('position.salary')}</td>
                    <td><input class="easyui-textbox search-input" id="query_salary" name="query.salary"></td>
                    <td>${euler:i18n('position.hot')}</td>
                    <td><input class="easyui-combobox search-input" id="query_hot" name="query.hot"
                            data-options="panelHeight:'auto',panelMaxHeight:'200px'"></td>
                    <td>${euler:i18n('position.pubDate')}</td>
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
                url:'findPositionByPage',
                toolbar:'#toolbar',
                fitColumns:false,
                rownumbers:false,
                remoteSort:false,
                pagination:true,
                singleSelect:false,
                onDblClickRow:onDblClickRow">
            <thead>
                <tr>
                    <th data-options="field:'ck', checkbox:true"></th>
                    <th data-options="field:'id',hidden:true">ID</th>
                    <th data-options="field:'name',align:'center',width:'200px'">${euler:i18n('position.name')}</th>
                    <th data-options="field:'summary',align:'center',width:'200px'">${euler:i18n('position.summary')}</th>
                    <th data-options="field:'acco',align:'center',width:'80px',formatter:accoFormatter">${euler:i18n('position.acco')}</th>
                    <th data-options="field:'acType',align:'center',width:'80px'">${euler:i18n('position.acType')}</th>
                    <th data-options="field:'companyName',align:'center',width:'100px'">${euler:i18n('position.companyName')}</th>
                    <th data-options="field:'salary',align:'center',width:'160px'">${euler:i18n('position.salary')}</th>
                    <th data-options="field:'pubDate',align:'center',formatter:unixDateFormatter">${euler:i18n('position.pubDate')}</th>
                    <th data-options="field:'hot',align:'center',formatter:yesOrNoFormatter">${euler:i18n('position.hot')}</th>
                    
                </tr>
            </thead>
        </table>
        <div id="dlg" class="easyui-dialog dlg-window" style="width:380px;"
                data-options="
                    closed:true,
                    iconCls:'icon-save',
                    resizable:false,
                    modal:true,
                    onClose:clearDlg,
                    constrain:true,
                    buttons:[{text:'${euler:i18n('global.save')}', iconCls:'icon-ok', handler:onSave},{text:'${euler:i18n('global.cancel')}', iconCls:'icon-cancel', handler:onCancel}]">
            <form id="fm" class="dlg-form" enctype="multipart/form-data" method="post">
                <div class="dlg-body">
                <input type="hidden" id="dlg_id" name="id">
                <div class="dlg-line">
                    <span class="dlg-label-span">
                        <label class="dlg-label">${euler:i18n('position.name')}
                    </span>
                    <span class="dlg-input-span">
                        <input class="easyui-textbox dlg-input" data-options="required:true" id="dlg_name" name="name">
                        &nbsp;&nbsp;<input id="ck_hot" type="checkbox" value="true" name="hot"><font color="red">${euler:i18n('position.hot')}</font></label></label>
                    </span>
                </div>
                <div class="dlg-line">
                    <span class="dlg-label-span">
                        <label class="dlg-label">${euler:i18n('position.companyId')}</label>
                    </span>
                    <span class="dlg-input-span">
                        <input class="easyui-combobox dlg-input" data-options="required:true,panelHeight:'auto',panelMaxHeight:'200px'" id="dlg_companyId" name="companyId">
                    </span>
                </div>
                <div class="dlg-line">
                    <span class="dlg-label-span">
                        <label class="dlg-label">${euler:i18n('position.acco')}</label>
                    </span>
                    <span class="dlg-input-span">
                        <input class="easyui-combobox dlg-input" data-options="required:true,panelHeight:'auto',panelMaxHeight:'200px'" id="dlg_acco" name="acco">
                    </span>
                </div>
                <div class="dlg-line">
                    <span class="dlg-label-span">
                        <label class="dlg-label">${euler:i18n('position.acType')}</label>
                    </span>
                    <span class="dlg-input-span">
                        <input class="easyui-textbox dlg-input" data-options="required:true" id="dlg_acType" name="acType">
                    </span>
                </div>
                <div class="dlg-line" style="height:93px;">
                    <span class="dlg-label-span">
                        <label class="dlg-label">${euler:i18n('position.summary')}</label>
                    </span>
                    <span class="dlg-input-span">
                        <input class="easyui-textbox dlg-input" style="height:87px;" data-options="required:true,multiline:true" id="dlg_summary" name="summary">
                    </span>
                </div>
                <div class="dlg-line">
                    <span class="dlg-label-span">
                        <label class="dlg-label">${euler:i18n('position.salary')}</label>
                    </span>
                    <span class="dlg-input-span">
                        <input class="easyui-textbox dlg-input" data-options="required:true" id="dlg_salary" name="salary">
                    </span>
                </div>
                <div class="dlg-line">
                    <span class="dlg-label-span">
                        <label class="dlg-label">${euler:i18n('position.pubDate')}</label>
                    </span>
                    <span class="dlg-input-span">
                        <input class="easyui-datetimebox dlg-input" data-options="required:true" id="dlg_pubDateStr" name="pubDateStr">
                    </span>
                </div>
                </div>
            </form>
        </div>        
    </div>
    <%@ include file="/WEB-INF/commonPages/easyui-js.jsp"%>

    <script>
        $(function(){
            
            $('#query_acco').combobox({
                valueField:'key',
                textField:'value',
                editable:false,
                data:getDictData(acco, 'all')
            });
            
            $('#dlg_acco').combobox({
                valueField:'key',
                textField:'value',
                editable:false,
                data:getDictData(acco)
            });
            
            $('#query_hot').combobox({
                valueField:'key',
                textField:'value',
                editable:false,
                data:getDictData(yesOrNo, 'all')
            });
            
            $.ajax({
                url:'findPartnerAll',
                type:'GET',
                async:true,
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    $.messager.alert("${euler:i18n('global.error')}", XMLHttpRequest.responseText);
                },
                success:function(data, textStatus) {
                    
                    $('#query_companyId').combobox({
                        valueField:'id',
                        textField:'name',
                        editable:false,
                        data:data.concat([{id:'', name:'全部'}])
                    });

                    $('#dlg_companyId').combobox({
                        valueField:'id',
                        textField:'name',
                        editable:false,
                        data:data
                    });                                  
                }
            });
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
            $('#dlg_pubDateStr').datetimebox('setValue', unixDateFormatter((new Date()).getTime()));
            $('#dlg').dialog('open').dialog('setTitle', "${euler:i18n('jsp.position.addPosition')}");
        }
        
        function onEdit() {
            var row = $('#dg').datagrid('getSelections');
            
            if(row == null || row.length < 1){
                $.messager.alert("${euler:i18n('global.remind')}", "${euler:i18n('global.pleaseSelectRowsToEdit')}");
            } else if(row){
                $('#fm').form('load', row[0]);
                $('#dlg_pubDateStr').datetimebox('setValue', unixDateFormatter(row[0].pubDate));
                $('#dlg').dialog('open').dialog('setTitle', "${euler:i18n('jsp.position.editPosition')}");
                
            }
        }
        
        function onSave() {
            $('#fm').form('submit', {
                url:'savePosition',
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
                            url:'deletePositions',
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
        
        function accoFormatter(value, row, index){
            if(typeof(value) == 'undefined' || value === '')
                return '-';
            for(i in acco){
                if(acco[i].key == value+"")
                    return acco[i].value;
            }    
        }
    </script>
</body>

</html>