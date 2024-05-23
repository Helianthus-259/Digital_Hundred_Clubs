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
    justify-content: space-between;
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
            <t-button theme="primary" @click="openDialog" variant="outline">发布活动</t-button>
            <t-input placeholder="输入你想搜索的内容" style="width: 350px;">
                <template #suffix-icon>
                    <t-icon name="search" />
                </template>
            </t-input>
        </div>
        <div class="listCardItem">
            <t-row :gutter="[
                { xs: 6, sm: 12, md: 18, lg: 24, xl: 24, xxl: 32 },
                { xs: 6, sm: 12, md: 18, lg: 24, xl: 24, xxl: 32 },
            ]">
                <t-col :span="3" v-for="(item, index) in activityView" :key="index">
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
                            2222
                        </template>
                    </t-card>
                </t-col>
            </t-row>
        </div>
        <div class="listCardPagination">
            <t-pagination v-model="current" v-model:pageSize="pageSize" :total="activityNumber" theme="simple"
                :pageSizeOptions="['12', '24', '36']" @page-size-change="onPageSizeChange"
                @current-change="onCurrentChange" />
        </div>
    </div>

    <myDialog ref="dialogRef">
        <template #header>
            发布活动
        </template>
        <ckeditor :editor="editor" v-model="newActivityContent" :config="editorConfig"></ckeditor>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="submitNewActivity">提交</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { ref } from 'vue';
import myDialog from '../myDialog.vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum } from '@/Enum';

// 请求活动需要的数据
const route = useRoute();
const clubId = route.params.cid
const pNumber = 0
const pSize = 48
// 保存请求到的活动数据
const activityList = ref([])
const activityNumber = ref(0)
// 展示的活动数据
const activityView = ref([])

eventEmitter.emit(APIEventEnum.request, APIEnum.getClubActivityList, { clubId, pNumber, pSize })

eventEmitter.on(APIEventEnum.getClubActivityListSuccess, (data) => {
    activityList.value.push(...data)
    activityNumber.value = activityList.value.length
    // 初始化展示的活动数据
    activityView.value = activityList.value.slice(0, pageSize.value)
    pNumber++
})

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

// 打开发布活动对话框
const dialogRef = ref(null);
const openDialog = () => {
    dialogRef.value.openDialog();
};

// 关闭发布活动对话框
const closeDialog = () => {
    dialogRef.value.closeDialog();
};

// 新活动内容
const newActivityContent = ref('')

// 提交新活动
const submitNewActivity = () => {

};

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
</script>