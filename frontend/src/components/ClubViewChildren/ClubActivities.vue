<style scoped>
.activityPublishBox {
    width: 100%;
    max-height: 670px;
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
            <div style="width: 100%; display: flex; justify-content: space-around;">
                <div style="display: flex; align-items: center;">
                    <label for="select">活动状态：</label>
                    <t-select id="select" v-model="statusSelect" @change="handleStatusChange" style="width: 200px;"
                        placeholder="请选择">
                        <t-option :value="-1" label="全部"></t-option>
                        <t-option :value="0" label="未开始"></t-option>
                        <t-option :value="1" label="进行中"></t-option>
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
</template>

<script setup>
import { useRoute } from 'vue-router';
import { onMounted, onUnmounted, reactive, ref } from 'vue';
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

// 活动状态表示
const activityStatusContent = {
    0: '未开始',
    1: '进行中',
}

const activityStatusTheme = {
    0: 'primary',
    1: 'success',
}

const activityEffect = ref('')

const selectChangeHandler = (fileList) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadImage, fileList[0])
}

onMounted(() => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getClubActivityList, { clubId, pNumber, pSize })
    eventEmitter.on(APIEventEnum.getClubActivityListSuccess, 'getClubActivityListSuccess', (data) => {
        data.forEach(activity => {
            if (activity.status !== 2)
                activityList.value.push(activity)
        })
        activityNumber.value = activityList.value.length
        // 初始化展示的活动数据
        activityView.value = activityList.value.slice((current.value - 1) * pageSize.value, current.value * pageSize.value)
        pNumber++
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getClubActivityListSuccess, 'getClubActivityListSuccess')
})
</script>