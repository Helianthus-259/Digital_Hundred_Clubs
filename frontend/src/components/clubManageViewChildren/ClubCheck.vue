<style scoped>
.clubCheckContainer {
    width: 100%;
    height: 690px;
    background: #ffffff;
    border-radius: 10px;
    overflow-y: auto;
}

.operationContainer {
    height: 10%;
    width: 90%;
    margin: 0 auto;
}

.tableContainer {
    height: 90%;
    width: 90%;
    margin: 0 auto;
}
</style>

<template>
    <div class="clubCheckContainer">
        <div class="operationContainer">
            <t-row style="height: 100%; display: flex; align-items: center;">
                <t-col :span="10">
                    <t-row :gutter="16">
                        <t-col :flex="1">
                            <t-form>
                                <t-form-item label="申请状态">
                                    <t-select style="width: 100%;" placeholder="请选择">
                                        <t-option value="待审核">待审核</t-option>
                                        <t-option value="已通过">已通过</t-option>
                                        <t-option value="未通过">未通过</t-option>
                                    </t-select>
                                </t-form-item>
                            </t-form>
                        </t-col>
                        <t-col :flex="1">
                            <t-form>
                                <t-form-item labelAlign="right" label="申请状态">
                                    <t-select style="width: 100%;" placeholder="请选择">
                                        <t-option value="待审核">待审核</t-option>
                                        <t-option value="已通过">已通过</t-option>
                                        <t-option value="未通过">未通过</t-option>
                                    </t-select>
                                </t-form-item>
                            </t-form>
                        </t-col>
                    </t-row>
                </t-col>
                <t-col :span="2" style="display: flex; justify-content: space-around;">
                    <t-button theme="primary" style="margin: 0 5px;">重置</t-button>
                    <t-button theme="default" style="margin: 0 5px;">查询</t-button>
                </t-col>
            </t-row>
        </div>
        <div class="tableContainer">
            <t-table row-key="studentId" :columns="columns" bordered :data="applyList">
            </t-table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';


const route = useRoute();
const clubId = route.params.cid

const applyList = ref([])

const columns = [
    { colKey: 'studentName', title: '申请人名称' },
    { colKey: 'status', title: '申请状态' },
    { colKey: 'email', title: '邮箱地址', ellipsis: true },
    { colKey: 'createTime', title: '申请时间' },
    { colKey: 'operations', title: '操作' },
]
</script>