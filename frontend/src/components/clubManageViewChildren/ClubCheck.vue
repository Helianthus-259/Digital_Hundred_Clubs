<style scoped>
.clubCheckContainer {
    width: 100%;
    height: 690px;
    background: #ffffff;
    border-radius: 10px;
    overflow-y: auto;
}

/*滚动条样式*/
.clubCheckContainer::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.clubCheckContainer::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
}

.clubCheckContainer::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.clubCheckContainer::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
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
                                    <t-select v-model="statusSelect" @change="handleStatusChange" style="width: 100%;"
                                        placeholder="请选择">
                                        <t-option :value="0" label="待审核"></t-option>
                                        <t-option :value="1" label="已通过"></t-option>
                                        <t-option :value="2" label="未通过"></t-option>
                                    </t-select>
                                </t-form-item>
                            </t-form>
                        </t-col>
                        <t-col :flex="1">
                            <t-form>
                                <t-form-item label="学院">
                                    <t-select v-model="collegeSelect" @change="handleCollegeChange" style="width: 100%;"
                                        placeholder="请选择">
                                        <t-option value="计算机学院" label="计算机学院"></t-option>
                                    </t-select>
                                </t-form-item>
                            </t-form>
                        </t-col>
                        <t-col :flex="1">
                            <t-form>
                                <t-form-item labelAlign="right" label="政治面貌">
                                    <t-select v-model="politicalStatusSelect" @change="handlePoliticalStatusChange"
                                        style="width: 100%;" placeholder="请选择">
                                        <t-option value="群众" label="群众"></t-option>
                                        <t-option value="共青团员" label="共青团员"></t-option>
                                        <t-option value="中共党员" label="中共党员"></t-option>
                                    </t-select>
                                </t-form-item>
                            </t-form>
                        </t-col>
                    </t-row>
                </t-col>
                <t-col :span="2" style="display: flex; justify-content: space-around;">
                    <t-button theme="primary" style="margin: 0 5px;" @click="handleSearch">查询</t-button>
                    <t-button theme="default" style="margin: 0 5px;" @click="handleReset">重置</t-button>
                </t-col>
            </t-row>
        </div>
        <div class="tableContainer">
            <t-table row-key="studentId" :columns="columns" :data="applyViewList" :expanded-row-keys="expandedRowKeys"
                :expand-icon="true" @expand-change="rehandleExpandChange">
                <template #expandedRow="{ row, index }">
                    <div>申请理由：{{ applyViewList[index].reason }}</div>
                </template>
                <template #status="{ row }">
                    <t-tag shape="round" :theme="statusNameListMap[row.status].theme" variant="light-outline">
                        <template #icon>
                            <t-icon :name="statusNameListMap[row.status].icon" />
                        </template>
                        {{ statusNameListMap[row.status].label }}
                    </t-tag>
                </template>
                <template #operations="{ row }">
                    <t-link :disabled="row.status !== 0" hover="underline" theme="primary" content="同意"
                        @click="agreeApply(row)">
                    </t-link>
                    &nbsp;&nbsp;
                    <t-link :disabled="row.status !== 0" hover="underline" theme="primary" content="拒绝"
                        @click="rejectApply(row)">
                    </t-link>
                </template>
            </t-table>
        </div>
    </div>

    <myDialog ref="dialogRef">
        <template #header>拒绝理由</template>
        <t-textarea v-model="rejectReason" placeholder="输入拒绝理由" :autosize="{ minRows: 21, maxRows: 21 }" />
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="submitReject">提交</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>
</template>

<script setup>
import { onMounted, onUnmounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import myDialog from '../myDialog.vue';
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum } from '@/Enum';
import { MessagePlugin } from 'tdesign-vue-next';

const statusNameListMap = {
    0: { label: '待审核', theme: 'primary', icon: 'error-circle-filled' },
    1: { label: '已通过', theme: 'success', icon: 'check-circle-filled' },
    2: { label: '未通过', theme: 'danger', icon: 'close-circle-filled' },
};


const route = useRoute();
const clubId = route.params.cid
// 弹窗
const dialogRef = ref(null)

const applyList = ref([])
const applyViewList = ref([])
// 选择查询
const statusSelect = ref(0)

const handleStatusChange = (value) => {
    statusSelect.value = value
};

const collegeSelect = ref('')

const handleCollegeChange = (value) => {
    collegeSelect.value = value
};

const politicalStatusSelect = ref('')

const handlePoliticalStatusChange = (value) => {
    politicalStatusSelect.value = value
};


// 查询
const handleSearch = () => {
    applyViewList.value = applyList.value.filter(item => {
        if (statusSelect.value === '' && collegeSelect.value === '' && politicalStatusSelect.value === '') {
            return true
        }
        return (
            item.status === statusSelect.value ||
            item.college === collegeSelect.value ||
            item.politicalStatus === politicalStatusSelect.value
        )
    })
};
// 重置查询选项
const handleReset = () => {
    statusSelect.value = ''
    collegeSelect.value = ''
    politicalStatusSelect.value = ''

    applyViewList.value = applyList.value
};


// 同意申请
const agreeApply = (row) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postAgreeClubApply, { clubId, studentId: row.studentId })
};

// 拒绝申请
const rejectReason = ref('')
let rejectRow = {}
const rejectApply = (row) => {
    rejectRow = row
    dialogRef.value.openDialog();
};

const closeDialog = () => {
    rejectReason.value = ''
    dialogRef.value.closeDialog();
};

const submitReject = () => {
    if (rejectReason.value !== '') {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postRejectClubApply, { clubId, studentId: rejectRow.studentId, rejectReason: rejectReason.value })
    }
};

// 表格的表头
const columns = [
    { colKey: 'stName', title: '申请人名称' },
    { colKey: 'college', title: '学院' },
    { colKey: 'politicalStatus', title: '政治面貌' },
    { colKey: 'status', title: '申请状态' },
    { colKey: 'email', title: '邮箱地址', ellipsis: true },
    { colKey: 'createTime', title: '申请时间' },
    { colKey: 'operations', title: '操作' },
]


// 展开的行
const expandedRowKeys = ref([]);
const rehandleExpandChange = (value, params) => {
    expandedRowKeys.value = value;
    console.log('rehandleExpandChange', value, params);
};

onMounted(() => {
    // 获取申请列表
    eventEmitter.emit(APIEventEnum.request, APIEnum.getClubApplyList, { clubId })

    eventEmitter.on(APIEventEnum.getClubApplyListSuccess, 'getClubApplyListSuccess', (data) => {
        applyList.value = data
        handleSearch()
    })

    eventEmitter.on(APIEventEnum.postRejectClubApplySuccess, 'postRejectClubApplySuccess', (studentId) => {
        MessagePlugin.success('拒绝申请成功')
        applyList.value.forEach(item => {
            if (item.studentId === studentId) {
                item.status = 2
            }
        })
        handleSearch()
        closeDialog()
    })

    eventEmitter.on(APIEventEnum.postAgreeClubApplySuccess, 'postAgreeClubApplySuccess', (studentId) => {
        MessagePlugin.success('同意申请成功')
        applyList.value.forEach(item => {
            if (item.studentId === studentId) {
                item.status = 1
            }
        })
        handleSearch()
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.postAgreeClubApplySuccess, 'postAgreeClubApplySuccess')
    eventEmitter.off(APIEventEnum.postRejectClubApplySuccess, 'postRejectClubApplySuccess')
    eventEmitter.off(APIEventEnum.getClubApplyListSuccess, 'getClubApplyListSuccess')
})
</script>