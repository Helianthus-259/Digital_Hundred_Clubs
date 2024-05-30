<style scoped>
.activityPublishBox {
    width: 100%;
    max-height: 690px;
    overflow-y: auto;
}

/*滚动条样式*/
.activityPublishBox::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.activityPublishBox::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
}

.activityPublishBox::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.activityPublishBox::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

.listCardOperation {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
    padding: 0 20px;
}

.listCardItem {
    margin: 12px 0 16px 0;
    padding: 0 20px;
}

.listCardPagination {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 20px;
    margin-bottom: 5px;
}

.t-card :deep(.t-card__cover) {
    height: 200px;
    overflow: hidden;
    display: flex;
    align-items: center;
}

.t-card__cover img {
    width: 100%;
    height: auto;
    object-fit: cover;
    object-position: center;
}
</style>

<template>
    <div class="activityPublishBox">
        <div class="listCardOperation">
            <div style="width: 10%; display: flex; justify-content: center;">
                <t-button theme="primary" @click="openDialog" variant="outline">发布活动</t-button>
            </div>
            <div style="width: 90%; display: flex; justify-content: space-around;">
                <div style="display: flex; align-items: center;">
                    <label for="select">活动状态：</label>
                    <t-select id="select" v-model="statusSelect" @change="handleStatusChange" style="width: 200px;"
                        placeholder="请选择">
                        <t-option :value="-1" label="全部"></t-option>
                        <t-option :value="0" label="未开始"></t-option>
                        <t-option :value="1" label="进行中"></t-option>
                        <t-option :value="2" label="已结束"></t-option>
                    </t-select>
                </div>
                <div style="display: flex; align-items: center;">
                    <t-input v-model="searchValue" placeholder="输入你想搜索的内容" style="width: 200px;">
                        <template #suffix-icon>
                            <t-icon name="search" />
                        </template>
                    </t-input>
                    <t-button theme="primary" variant="outline" style="margin: 0 5px;"
                        @click="handleSearch">查询</t-button>
                    <t-button theme="default" variant="outline" style="margin: 0 5px;"
                        @click="handleReset">重置</t-button>
                </div>
            </div>
        </div>
        <div class="listCardItem">
            <t-row :gutter="[
                { xs: 6, sm: 12, md: 18, lg: 24, xl: 24, xxl: 32 },
                { xs: 6, sm: 12, md: 18, lg: 24, xl: 24, xxl: 32 },
            ]">
                <t-col :span="4" v-for="(item, index) in activityView" :key="index">
                    <t-card>
                        <template #header>
                            {{ item.activityName }}
                            <t-tag shape="round" variant="light-outline" :theme="activityStatusTheme[item.status]"
                                :content="activityStatusContent[item.status]"></t-tag>
                        </template>
                        <template #cover>
                            <img :src="item.imageUrl">
                        </template>
                        <template #footer>
                            <div style="display: flex; justify-content: end;">
                                <t-button :disabled="item.status !== 2" variant="text" shape="square"
                                    :style="{ 'margin-right': '8px' }"
                                    @click="openUploadPerformance(item.activityId, true)">
                                    <t-popup content="上传活动中的个人成效" placement="top" show-arrow>
                                        <t-icon name="upload" />
                                    </t-popup>
                                </t-button>
                                <t-button :disabled="item.status !== 2" variant="text" shape="square"
                                    :style="{ 'margin-right': '8px' }"
                                    @click="openUploadPerformance(item.activityId, false)">
                                    <t-popup content="上传活动成效" placement="top" show-arrow>
                                        <t-icon name="building" />
                                    </t-popup>
                                </t-button>
                            </div>
                        </template>
                    </t-card>
                </t-col>
            </t-row>
        </div>
        <div v-show="paginationShow" class="listCardPagination">
            <t-pagination v-model="current" v-model:pageSize="pageSize" :total="activityNumber" theme="simple"
                :pageSizeOptions="['12', '24', '36']" @page-size-change="onPageSizeChange"
                @current-change="onCurrentChange" />
            <t-button shape="square" variant="text" @click="loadMoreData">
                <template #icon>
                    <t-popup content="加载更多" placement="top" showArrow>
                        <t-icon name="more" />
                    </t-popup>
                </template>
            </t-button>
        </div>
    </div>

    <myDialog ref="dialogRef">
        <template #header>
            发布活动
        </template>
        <t-form style="width: 800px;" :data="newActivityForm">
            <t-form-item label="活动名称">
                <t-input v-model="newActivityForm.activityName" style="width: 300px;" clearable placeholder="请输入活动名称" />
            </t-form-item>
            <t-form-item label="活动封面">
                <t-upload :image-viewer-props="{ closeOnEscKeydown: false }" :size-limit="{ size: 500, unit: 'KB' }"
                    theme="image" accept="image/*" :auto-upload="false" :show-image-file-name="true" :locale="{
                        triggerUploadText: {
                            image: '请选择图片',
                        },
                    }" :onSelectChange="selectChangeHandler">
                </t-upload>
            </t-form-item>
            <t-form-item label="开始时间">
                <t-date-picker v-model="newActivityForm.activityStartTime" enable-time-picker allow-input clearable />
            </t-form-item>
            <t-form-item label="结束时间">
                <t-date-picker v-model="newActivityForm.activityEndTime" enable-time-picker allow-input clearable />
            </t-form-item>
            <t-form-item label="活动地点">
                <t-input v-model="newActivityForm.activityLocation" style="width: 300px;" clearable
                    placeholder="请输入活动地点" />
            </t-form-item>
            <t-form-item label="活动介绍">
                <div style="width: 80%;">
                    <ckeditor :editor="editor" v-model="newActivityForm.activityIntroduction" :config="editorConfig">
                    </ckeditor>
                </div>
            </t-form-item>
            <t-form-item label="附件">
                <t-upload></t-upload>
            </t-form-item>
        </t-form>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="submitNewActivity">提交</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>

    <!-- 上传个人绩效弹窗 -->
    <myDialog ref="uploadPerformanceDialogRef">
        <template #header>
            <div style="font-size: 24px; font-weight: bold;">{{ personalOrActivity ? '个人绩效' : '活动绩效' }}</div>
        </template>
        <div style="width: 600px;" v-show="personalOrActivity">
            <div style="margin-bottom: 20px;">
                <t-upload :autoUpload="false" :onSelectChange="fileSelectChangeHandler"
                    :onRemove="fileRemoveHandler"></t-upload>
            </div>
            <div style="width: 90%;">
                <t-table row-key="studentNumber" :columns="excelColumns" :data="excelData" bordered></t-table>
            </div>
        </div>
        <div v-show="!personalOrActivity">
            <div style="margin-top: 30px;">
                <t-form>
                    <t-form-item label="活动绩效" name="activityEffect">
                        <t-input v-model="activityEffect" style="width: 300px;" clearable />
                    </t-form-item>
                </t-form>
            </div>
        </div>
        <template #footer>
            <t-button v-show="personalOrActivity" style="margin: 0 10px;" theme="primary" variant="outline"
                @click="submitPersonalEffect">提交</t-button>
            <t-button v-show="!personalOrActivity" style="margin: 0 10px;" theme="primary" variant="outline"
                @click="submitActivityEffect">提交</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline"
                @click="closeUploadPerformance">关闭</t-button>
        </template>
    </myDialog>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { onMounted, onUnmounted, reactive, ref } from 'vue';
import myDialog from '../myDialog.vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum } from '@/Enum';
import { MessagePlugin } from 'tdesign-vue-next';
import * as XLSX from 'xlsx'

// 请求活动需要的数据
const route = useRoute();
const clubId = route.params.cid
let pNumber = 0
const pSize = 48
// 保存请求到的活动数据
const activityList = ref([])
const activityNumber = ref(0)
// 展示的活动数据
const activityView = ref([])

// 查询不同状态的活动
const paginationShow = ref(true)
const statusSelect = ref(-1)
const handleStatusChange = (value) => {
    // 重置查找的内容
    searchValue.value = ''
    if (value === -1) {
        activityView.value = activityList.value.slice((current.value - 1) * pageSize.value, current.value * pageSize.value)
        paginationShow.value = true
    } else {
        activityView.value = activityList.value.filter(activity => activity.status === value)
        paginationShow.value = false
    }
}

// 通过查找框查找活动
const searchValue = ref('')

const handleSearch = () => {
    // 重置状态选择
    statusSelect.value = -1
    if (searchValue.value !== '') {
        activityView.value = activityList.value.filter(activity => activity.activityName.includes(searchValue.value))
        paginationShow.value = false
    }
}

const handleReset = () => {
    statusSelect.value = -1
    activityView.value = activityList.value.slice((current.value - 1) * pageSize.value, current.value * pageSize.value)
    searchValue.value = ''
    paginationShow.value = true
}

// 获取活动信息
eventEmitter.emit(APIEventEnum.request, APIEnum.getClubActivityList, { clubId, pNumber, pSize })
const loadMoreData = () => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getClubActivityList, { clubId, pNumber, pSize })
}

// 分页条设置
const current = ref(1);
const pageSize = ref(12);
const onPageSizeChange = (size, pageInfo) => {
    current.value = pageInfo.current
    activityView.value = activityList.value.slice((current.value - 1) * size, current.value * size)
};
const onCurrentChange = (index) => {
    activityView.value = activityList.value.slice((index - 1) * pageSize.value, index * pageSize.value)
};

// 新活动
const newActivityForm = reactive({
    activityName: '',
    activityStartTime: '',
    activityEndTime: '',
    activityLocation: '',
    activityIntroduction: '',
    applicationFormAttachment: '',
    imageUrl: ''
})

// 打开发布活动对话框
const dialogRef = ref(null);
const openDialog = () => {
    dialogRef.value.openDialog();
};

// 关闭发布活动对话框
const closeDialog = () => {
    newActivityForm.activityName = ''
    newActivityForm.activityStartTime = ''
    newActivityForm.activityEndTime = ''
    newActivityForm.activityLocation = ''
    newActivityForm.activityIntroduction = ''
    newActivityForm.applicationFormAttachment = ''
    newActivityForm.imageUrl = ''
    dialogRef.value.closeDialog();
};

// 提交新活动
const submitNewActivity = () => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postNewActivity, { clubId, ...newActivityForm })
};

// 打开上传个人绩效对话框
const uploadPerformanceDialogRef = ref(null);
const personalOrActivity = ref(true)
let curActivityId = -1
const openUploadPerformance = (activityId, flag) => {
    uploadPerformanceDialogRef.value.openDialog();
    curActivityId = activityId
    personalOrActivity.value = flag
};

// 关闭上传个人绩效对话框
const closeUploadPerformance = () => {
    excelData.value = []
    activityEffect.value = ''
    uploadPerformanceDialogRef.value.closeDialog();
};

const submitPersonalEffect = () => {
    if (excelData.value.length === 0) {
        MessagePlugin.warning('请先上传个人绩效文件')
        return
    } else {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postPersonalPerformance, { clubId, activityId: curActivityId, personalEffectList: excelData.value })
    }
}

const submitActivityEffect = () => {
    if (activityEffect.value === '') {
        MessagePlugin.warning('请先填写活动效果')
        return
    } else {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postActivityPerformance, { clubId, activityId: curActivityId, activityEffect: activityEffect.value })
    }
}

// 富文本编辑器
const editor = ClassicEditor
const editorConfig = {
    // 配置编辑器
    toolbar: {
        items: [
            'heading',
            '|',
            'bold',
            'italic',
            '|',
            'bulletedList',
            'numberedList',
            '|',
            'link',
            'uploadImage',
            '|',
            'undo',
            'redo'
        ]
    },
    heading: {
        options: [
            { model: 'paragraph', title: '正文', class: 'ck-heading_paragraph' },
            { model: 'heading2', view: 'h2', title: '二级标题', class: 'ck-heading_heading2' },
            { model: 'heading3', view: 'h3', title: '三级标题', class: 'ck-heading_heading3' },
        ]
    }
}

// 活动状态表示
const activityStatusContent = {
    0: '未开始',
    1: '进行中',
    2: '已结束'
}

const activityStatusTheme = {
    0: 'primary',
    1: 'success',
    2: 'warning'
}

const activityEffect = ref('')

const selectChangeHandler = (fileList) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadImage, fileList[0])
}

// 上传excel文件
const fileSelectChangeHandler = (fileList) => {
    const file = fileList[0]
    const fileName = file.name
    const fileType = fileName.split('.').pop()
    if (fileType === 'xlsx' || fileType === 'xls') {
        importExcelFile(file)
    } else {
        MessagePlugin.warning('请上传Excel文件')
        return
    }
}

const fileRemoveHandler = () => {
    excelData.value = []
}

const excelData = ref([])
const excelColumns = [
    { colKey: 'stName', title: '姓名' },
    { colKey: 'studentNumber', title: '学号' },
    { colKey: 'personalEffect', title: '个人成效' },
]

const importExcelFile = (file) => {
    const fileReader = new FileReader()
    fileReader.readAsArrayBuffer(file)
    fileReader.onload = (e) => {
        const data = e.target.result
        const workbook = XLSX.read(data, { type: 'array' })
        const worksheet = workbook.Sheets[workbook.SheetNames[0]]
        const jsonData = XLSX.utils.sheet_to_json(worksheet)
        jsonData.forEach((item) => {
            const keys = Object.keys(item)
            const values = Object.values(item)
            const newItem = {}
            excelColumns.forEach((col, index) => {
                newItem[col.colKey] = values[keys.indexOf(col.title)]
            });
            excelData.value.push(newItem)
        })
    }
}

onMounted(() => {
    eventEmitter.on(APIEventEnum.postNewActivitySuccess, 'postNewActivitySuccess', () => {
        MessagePlugin.success('发布成功')
        closeDialog()
    })
    eventEmitter.on(APIEventEnum.getClubActivityListSuccess, 'getClubActivityListSuccess', (data) => {
        activityList.value.push(...data)
        activityNumber.value = activityList.value.length
        // 初始化展示的活动数据
        activityView.value = activityList.value.slice((current.value - 1) * pageSize.value, current.value * pageSize.value)
        pNumber++
    })
    eventEmitter.on(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess', (data) => {
        newActivityForm.imageUrl = data.url
    })
    eventEmitter.on(APIEventEnum.postPersonalPerformanceSuccess, 'postPersonalPerformanceSuccess', () => {
        MessagePlugin.success('上传成功')
        excelData.value = []
        closeDialog()
    })
    eventEmitter.on(APIEventEnum.postActivityPerformanceSuccess, 'postActivityPerformanceSuccess', () => {
        MessagePlugin.success('上传成功')
        activityEffect.value = ''
        closeDialog()
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getClubActivityListSuccess, 'getClubActivityListSuccess')
    eventEmitter.off(APIEventEnum.postNewActivitySuccess, 'postNewActivitySuccess')
    eventEmitter.off(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess')
    eventEmitter.off(APIEventEnum.postPersonalPerformanceSuccess, 'postPersonalPerformanceSuccess')
    eventEmitter.off(APIEventEnum.postActivityPerformanceSuccess, 'postActivityPerformanceSuccess')
})
</script>