<style scoped>
.NoticeContainer {
    width: 100%;
    height: 690px;
    display: flex;
    justify-content: space-between;
    position: relative;
}

.NoticeBox {
    width: 50%;
    height: 690px;
}

.NoticeContent {
    width: 100%;
    height: 640px;
    background-color: #efefef;
    box-sizing: border-box;
    border: 10px solid #ffffff;
    border-top-left-radius: 8px;
    overflow-y: auto;
}

/*滚动条样式*/
.NoticeContent::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.NoticeContent::-webkit-scrollbar-track {
    background-color: #ffffff;
    /* 滚动条轨道背景色 */
}

.NoticeContent::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.NoticeContent::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

.operateBOX {
    width: 100%;
    height: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #ffffff;
    border-bottom-left-radius: 8px;
}

.publishBOX {
    width: 50%;
    background-color: #ffffff;
    height: 690px;
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;
}

.maskBox {
    width: 50%;
    height: 690px;
    background: url('/src/assets/mask.png') no-repeat center center;
    position: absolute;
    top: 0;
    right: 0;
    z-index: 100;
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
    transform: translateX(0);
    transition: transform 0.5s ease-in-out;
}

.maskBox--visible {
    transform: translateX(-100%);
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
}
</style>

<template>
    <div class="NoticeContainer">
        <div class="maskBox" :class="{ 'maskBox--visible': isMaskVisible }"></div>
        <div class="NoticeBox">
            <div class="NoticeContent">
                <t-list :split="true">
                    <template #header>
                        <div style="font-size: 24px; font-weight: bold;">往期通知</div>
                    </template>
                    <t-list-item v-for="(item, index) in noticeList" :key="index">
                        <template #content>
                            <div style="display: block; justify-content: center; width: 100%; ;">
                                <div style="font-size: 18px; font-weight: bold;">{{ item.title }}</div>
                                <div style="display: flex; justify-content: center;margin-top: 5px;margin-bottom: 5px;">
                                    <img style="width: 100%;" :src="item.imageUrl">
                                </div>
                                <div style="text-indent: 2em;">{{ item.content }}</div>
                                <div style="text-align: right;">{{ item.publishTime }}</div>
                            </div>
                        </template>
                    </t-list-item>
                </t-list>
            </div>
            <div class="operateBOX">
                <t-button variant="outline" theme="primary" @click="isMaskVisible = !isMaskVisible">发布新通知</t-button>
            </div>
        </div>
        <div class="publishBOX">
            <div style="width: 100%; height: 640px; display: flex; 
                justify-content:start; align-items:start; overflow-y: auto; box-sizing: border-box; padding: 10px;">
                <t-form colon>
                    <t-form-item requiredMark label="通知标题">
                        <t-input v-model="newNotice.title" placeholder="请输入通知标题" />
                    </t-form-item>
                    <t-form-item requiredMark label="通知类型">
                        <t-radio-group v-model="noticeType" @change="onTypeChange">
                            <t-radio :value="0">普通通知</t-radio>
                            <t-radio :value="1">会议通知</t-radio>
                        </t-radio-group>
                    </t-form-item>
                    <t-form-item requiredMark v-show="!noticeType" label="通知内容">
                        <t-textarea v-model="newNotice.content" placeholder="请输入通知内容"
                            :autosize="{ minRows: 6, maxRows: 9 }" />
                    </t-form-item>
                    <t-form-item requiredMark v-show="noticeType" label="会议类型">
                        <t-select v-model="newMeeting.category">
                            <t-option value="0" label="全员大会"></t-option>
                            <t-option value="1" label="骨干例会"></t-option>
                            <t-option value="1" label="其他"></t-option>
                        </t-select>
                    </t-form-item>
                    <t-form-item requiredMark v-show="noticeType" label="会议地点">
                        <t-input v-model="newMeeting.location" placeholder="请输入会议地点" />
                    </t-form-item>
                    <t-form-item requiredMark v-show="noticeType" label="会议时间">
                        <t-date-picker v-model="newMeeting.meetingTime" enable-time-picker clearable />
                    </t-form-item>
                    <t-form-item v-show="noticeType" label="指导教师">
                        <t-input v-model="newMeeting.advisorName" placeholder="请输入指导教师名字" />
                    </t-form-item>
                    <t-form-item label="通知图片">
                        <t-upload v-model="imageUpload" :image-viewer-props="{ closeOnEscKeydown: false }"
                            :size-limit="{ size: 500, unit: 'KB' }" theme="image" accept="image/*" :auto-upload="false"
                            :show-image-file-name="true" :locale="{
                                triggerUploadText: {
                                    image: '请选择图片',
                                },
                            }" :onSelectChange="selectChangeHandler"></t-upload>
                    </t-form-item>
                </t-form>
            </div>
            <div style="width: 100%; height: 50px; display: flex; justify-content:space-around;">
                <t-button variant="outline" theme="primary" @click="submitNotice">发布</t-button>
                <t-button variant="outline" theme="default" @click="isMaskVisible = !isMaskVisible">取消</t-button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import eventEmitter from '@/utils/eventEmitter';
import { MessagePlugin } from 'tdesign-vue-next';
import { onMounted, onUnmounted, ref } from 'vue';
import { useRoute } from 'vue-router';


const route = useRoute();
const clubId = route.params.cid;

const isMaskVisible = ref(false);

const noticeList = ref([])

const imageUpload = ref([])

const newNotice = ref({
    title: '',
    content: '',
    imageUrl: ''
})

const newMeeting = ref({
    meetingTime: '',
    location: '',
    category: '',
    advisorName: '',
})


const noticeType = ref(0)
const onTypeChange = (value) => {
    console.log(value);
}

const selectChangeHandler = (fileList) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadImage, { file: fileList[0] })
}

const submitNotice = () => {
    if (noticeType.value) {
        if (newMeeting.value.meetingTime === '' || newMeeting.value.location === '' || newMeeting.value.category === '') {
            MessagePlugin.warning('请填写完整信息')
            return
        }
        newNotice.value.content = '会议类型:' + newMeeting.value.category + '\n会议地点:' + newMeeting.value.location + '\n会议时间:' + newMeeting.value.meetingTime + '\n指导教师:' + newMeeting.value.advisorName
        eventEmitter.emit(APIEventEnum.request, APIEnum.postNewNotice, { clubId, ...newNotice.value })
        eventEmitter.emit(APIEventEnum.request, APIEnum.postNewMeeting, { clubId, ...newMeeting.value })
    } else {
        if (newNotice.value.title === '' || newNotice.value.content === '') {
            MessagePlugin.warning('请填写完整信息')
            return
        }
        eventEmitter.emit(APIEventEnum.request, APIEnum.postNewNotice, { clubId, ...newNotice.value })
    }
}

onMounted(() => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getClubNoticeList, { clubId })
    eventEmitter.on(APIEventEnum.getClubNoticeListSuccess, 'getClubNoticeListSuccess', (data) => {
        noticeList.value.push(...data)
    })
    eventEmitter.on(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess', (data) => {
        newNotice.value.imageUrl = data.url
        console.log(newNotice.value.imageUrl);
        MessagePlugin.success('上传成功')
    })
    eventEmitter.on(APIEventEnum.postNewNoticeSuccess, 'postNewNoticeSuccess', () => {
        const now = new Date()
        const year = now.getFullYear()
        const month = ('0' + (now.getMonth() + 1)).slice(-2)
        const day = ('0' + now.getDate()).slice(-2)
        const hours = ('0' + now.getHours()).slice(-2)
        const minutes = ('0' + now.getMinutes()).slice(-2)
        const seconds = ('0' + now.getSeconds()).slice(-2)

        const formattedTime = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
        noticeList.value.push({
            title: newNotice.value.title,
            content: newNotice.value.content,
            imageUrl: newNotice.value.imageUrl,
            publishTime: formattedTime,
            modifyTime: formattedTime,
        })
        isMaskVisible.value = !isMaskVisible
        if (!noticeType.value)
            MessagePlugin.success('发布通知成功')
        newNotice.value.title = ''
        newNotice.value.content = ''
        newNotice.value.imageUrl = ''
        imageUpload.value = []
    })
    eventEmitter.on(APIEventEnum.postNewMeetingSuccess, 'postNewMeetingSuccess', () => {
        MessagePlugin.success('发布会议成功')
        newMeeting.value.meetingTime = ''
        newMeeting.value.location = ''
        newMeeting.value.category = ''
        newMeeting.value.advisorName = ''
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getClubNoticeListSuccess, 'getClubNoticeListSuccess')
    eventEmitter.off(APIEventEnum.postNewNoticeSuccess, 'postNewNoticeSuccess')
    eventEmitter.off(APIEventEnum.postNewMeetingSuccess, 'postNewMeetingSuccess')
    eventEmitter.off(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess')
})
</script>