<style scoped>
.mainBox {
    display: flex;
    width: 100%;
    height: 600px;
}

/* 左边部分 */
.leftSide {
    width: 80%;
    padding: 5px;
}

.t-card {
    height: 100%;
}

/* 右边部分 */
.rightSide {
    width: 20%;
    padding: 5px;
}

/* 右边部分内容 */
.rightSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
}

.avatar {
    width: 100%;
    height: 250px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.ribbon {
    width: 100%;
    height: calc(100% - 250px);
    display: flex;
    justify-content: center;
    align-items: self-start;
}

.t-col {
    width: 50%;
    display: inline-flex;
    justify-content: center;

    .labelBox {
        width: 40%;
        display: inline-flex;
        justify-content: center;
        align-items: center;
        font-size: 16px;
    }

    .valueBox {
        width: 60%;
        display: inline-flex;
        justify-content: center;
        align-items: center;
        font-size: 16px;
    }
}
</style>

<template>
    <div class="mainBox">
        <div class="leftSide">
            <t-card title="个人信息">
                <template #default>
                    <t-row :gutter="[0, 54]">
                        <t-col>
                            <div class="labelBox">姓名：</div>
                            <div class="valueBox">{{ user.stName }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">学号：</div>
                            <div class="valueBox">{{ user.studentNumber }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">性别：</div>
                            <div class="valueBox">{{ user.gender === '1' ? '男' : '女' }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">年级：</div>
                            <div class="valueBox">{{ user.grade }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">电子邮件：</div>
                            <div class="valueBox">{{ user.email }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">生日：</div>
                            <div class="valueBox">
                                <t-date-picker v-model="user.dateOfBirth" allow-input :disabled="readOnly" />
                            </div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">学院：</div>
                            <div class="valueBox">
                                <t-input v-model="user.college" borderless :readonly="readOnly"
                                    align="center"></t-input>
                            </div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">政治面貌：</div>
                            <div class="valueBox">
                                <t-select v-model="user.politicalStatus" borderless :readonly="readOnly"
                                    :inputProps="{ align: 'center' }">
                                    <t-option value="群众" label="群众"></t-option>
                                    <t-option value="共青团员" label="共青团员"></t-option>
                                    <t-option value="中共党员" label="中共党员"></t-option>
                                </t-select>
                            </div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">联系方式：</div>
                            <div class="valueBox">
                                <t-input v-model="user.contact" borderless :readonly="readOnly" align="center" />
                            </div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">爱好：</div>
                            <div class="valueBox">
                                <t-input v-model="user.hobby" borderless :readonly="readOnly" align="center" />
                            </div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">特长：</div>
                            <div class="valueBox">
                                <t-input v-model="user.specialty" borderless :readonly="readOnly" align="center" />
                            </div>
                        </t-col>
                    </t-row>
                </template>
                <template #footer>
                    <div style="display: flex; justify-content: space-around;">
                        <t-button theme="primary" @click="editPersonalInfo" :disabled="!readOnly">编辑信息</t-button>
                        <t-button theme="default" @click="save" :disabled="readOnly">保存</t-button>
                    </div>
                </template>

            </t-card>
        </div>

        <div class="rightSide">
            <div class="rightSideContent">
                <div class="avatar">
                    <t-avatar v-show="readOnly" :image="user.imageUrl" size="200px"></t-avatar>
                    <t-upload v-show="!readOnly" :image-viewer-props="{ closeOnEscKeydown: false }"
                        :size-limit="{ size: 500, unit: 'KB' }" theme="image" accept="image/*" :auto-upload="false"
                        :show-image-file-name="true" :locale="{
                            triggerUploadText: {
                                image: '请选择图片',
                            },
                        }" :onSelectChange="selectImageChangeHandler">
                    </t-upload>
                </div>
                <div class="ribbon">
                    <t-button theme="default" @click="openDialog">我要创建社团！</t-button>
                </div>
            </div>
        </div>
    </div>
    <!-- 申请创建社团弹窗 -->
    <myDialog ref="dialogRef">
        <template #header>
            新建社团申请表
        </template>
        <div style="width: 700px;">
            <t-form :label-width="150" style="width: 80%; margin: 0 auto;" v-model="newClubFrom" colon requiredMark>
                <t-form-item label="社团名称">
                    <t-input v-model="newClubFrom.clubName" placeholder="请输入社团名称" />
                </t-form-item>
                <t-form-item label="社团简介">
                    <div style="width: 80%;">
                        <t-textarea v-model="newClubFrom.clubDescription[0].content" name="社团简介" placeholder="请输入社团简介"
                            autosize />
                    </div>
                </t-form-item>
                <t-form-item label="社团类型">
                    <mySelect v-model="newClubFrom.clubCategory" :options="clubTypeOptions"></mySelect>
                </t-form-item>
                <t-form-item label="社团负责人">
                    <t-input v-model="newClubFrom.leader" placeholder="请输入社团负责人名字" />
                </t-form-item>
                <t-form-item label="负责人电话">
                    <t-input v-model="newClubFrom.leaderPhone" placeholder="请输入社团负责人电话" />
                </t-form-item>
                <t-form-item label="社团所在校区">
                    <mySelect v-model="newClubFrom.mainCampus" :options="campusOptions"></mySelect>
                </t-form-item>
                <t-form-item label="社团所属院系">
                    <t-select v-model="newClubFrom.adminId" placeholder="请选择社团所属院系">
                        <t-option v-for="(item, index) in departmentList" :key="index" :value="item.adminId"
                            :label="item.departmentName" />
                    </t-select>
                </t-form-item>
                <t-form-item label="行政指导老师">
                    <t-input v-model="newClubFrom.administrativeGuideTeacherName" placeholder="请输入行政指导老师名字" />
                </t-form-item>
                <t-form-item label="业务指导老师">
                    <t-input v-model="newClubFrom.businessGuideTeacherName" placeholder="请输入业务指导老师名字" />
                </t-form-item>
                <t-form-item label="指导教师简历">
                    <t-upload :size-limit="{ size: 3000000, unit: 'B' }"
                        accept=".doc,.docx,.docm,.dot,.dotx,.dotm,.xls,.xlsx,.xlsm,.xlt,.xltx,.xltm,.xlsb,.xlam,.pdf"
                        :auto-upload="false" :onSelectChange="selectChangeHandler" @validate="onValidate">
                    </t-upload>
                </t-form-item>
            </t-form>
        </div>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="submitNewClub">发送</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>

</template>

<script setup>
import { APIEnum, APIEventEnum, StoreEnum, StoreEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { onMounted, onUnmounted, reactive, ref } from 'vue';
import myDialog from '../myDialog.vue';
import mySelect from '../mySelect.vue';
import { MessagePlugin } from 'tdesign-vue-next';

// 弹窗
const dialogRef = ref(null);

const openDialog = () => {
    dialogRef.value.openDialog();
};

const closeDialog = () => {
    newClubFrom.clubName = ''
    newClubFrom.clubDescription[0].content = ''
    newClubFrom.clubCategory = ''
    newClubFrom.leader = ''
    newClubFrom.leaderPhone = ''
    newClubFrom.mainCampus = ''
    newClubFrom.adminId = ''
    newClubFrom.administrativeGuideTeacherName = ''
    newClubFrom.businessGuideTeacherName = ''
    newClubFrom.advisorResumeAttachmentUrl = ''
    dialogRef.value.closeDialog();
};

// 新建社团
const departmentList = ref(store.state.departmentList)


const newClubFrom = reactive({
    studentId: store.state.studentId,
    clubName: '',
    clubDescription: [{ title: '社团介绍', content: '' }],
    clubCategory: '',
    leader: '',
    leaderPhone: '',
    mainCampus: '',
    adminId: '',
    administrativeGuideTeacherName: '',
    businessGuideTeacherName: '',
    advisorResumeAttachmentUrl: '',
})

// 获取leader的名字和电话
const getLeaderInfo = () => {
    newClubFrom.leader = user.value.stName
    newClubFrom.leaderPhone = user.value.contact
}

// 社团类型
const clubTypeOptions = [
    { label: '学术类', value: '0' },
    { label: '体育类', value: '1' },
    { label: '艺术类', value: '2' },
    { label: '公益类', value: '3' },
    { label: '科技类', value: '4' },
    { label: '其他类', value: '5' }
]

// 校区
const campusOptions = [
    { label: '广州南校', value: '1' },
    { label: '广州东校', value: '2' },
    { label: '广州北校', value: '0' },
    { label: '珠海校区', value: '3' },
    { label: '深圳校区', value: '4' },
]

// 上传文件限制
const onValidate = (context) => {
    if (context.type === 'FILE_OVER_SIZE_LIMIT') {
        Message.warning('文件大小超出上限');
    }
};

const selectChangeHandler = (fileList) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadFile, { file: fileList[0] })
}

const selectImageChangeHandler = (fileList) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadImage, { file: fileList[0] })
}

// 检验newClubFrom中的数据是否都已填写
const newClubFromValidate = () => {
    if (newClubFrom.clubName === '' || newClubFrom.clubDescription[0].content === '' || newClubFrom.clubCategory === '' || newClubFrom.leader === '' || newClubFrom.leaderPhone === '' || newClubFrom.mainCampus === '' || newClubFrom.adminId === '' || newClubFrom.administrativeGuideTeacherName === '' || newClubFrom.businessGuideTeacherName === '' || newClubFrom.advisorResumeAttachmentUrl === '') {
        MessagePlugin.warning('请将信息填写完整');
        return false;
    }
    return true;
}

const submitNewClub = () => {
    console.log(newClubFrom)
    if (newClubFromValidate()) {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postNewClubApply, newClubFrom)
    }
}


// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 展示个人信息
const user = ref({})

if (isEmptyObject(store.state.userInfo)) {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getUserInfo, { studentId: store.state.studentId })
} else {
    user.value = store.state.userInfo
    getLeaderInfo()
}

// 判断是否为只读模式
const readOnly = ref(true)

const oldUserInfo = {
    dateOfBirth: '',
    college: '',
    politicalStatus: '',
    contact: '',
    hobby: '',
    specialty: '',

}
const editPersonalInfo = () => {
    oldUserInfo.contact = user.value.contact
    oldUserInfo.hobby = user.value.hobby
    oldUserInfo.specialty = user.value.specialty
    oldUserInfo.politicalStatus = user.value.politicalStatus
    oldUserInfo.college = user.value.college
    oldUserInfo.dateOfBirth = user.value.dateOfBirth
    readOnly.value = !readOnly.value
    MessagePlugin.success('已进入编辑模式')
}

// 保存修改后的信息
function save() {
    if (user.value.contact === oldUserInfo.contact
        && user.value.hobby === oldUserInfo.hobby
        && user.value.specialty === oldUserInfo.specialty
        && user.value.politicalStatus === oldUserInfo.politicalStatus
        && user.value.college === oldUserInfo.college
        && user.value.dateOfBirth === oldUserInfo.dateOfBirth) {
        readOnly.value = !readOnly.value
        getLeaderInfo()
        MessagePlugin.success('保存成功')
    } else {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postUserInfo, {
            studentId: store.state.studentId,
            politicalStatus: user.value.politicalStatus,
            contact: user.value.contact,
            hobby: user.value.hobby,
            specialty: user.value.specialty,
            college: user.value.college,
            dateOfBirth: user.value.dateOfBirth
        })
    }
}

onMounted(() => {
    eventEmitter.on(APIEventEnum.getUserInfoSuccess, 'getUserInfoSuccess', (data) => {
        user.value = data
        getLeaderInfo()
    })
    eventEmitter.on(APIEventEnum.postUserInfoSuccess, 'postUserInfoSuccess', () => {
        eventEmitter.emit(StoreEventEnum.set, StoreEnum.setUserInfo, user.value)
        readOnly.value = !readOnly.value
        getLeaderInfo()
        MessagePlugin.success('保存成功')
    })
    eventEmitter.on(APIEventEnum.postNewClubApplySuccess, 'postNewClubApplySuccess', () => {
        closeDialog()
        MessagePlugin.success('提交成功')
    })
    eventEmitter.on(APIEventEnum.uploadFileSuccess, 'uploadFileSuccess', (data) => {
        MessagePlugin.success('文件上传成功')
        newClubFrom.advisorResumeAttachmentUrl = data.file.url
    })
    eventEmitter.on(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess', (data) => {
        MessagePlugin.success('图片上传成功')
        user.value.imageUrl = data.url
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getUserInfoSuccess, 'getUserInfoSuccess')
    eventEmitter.off(APIEventEnum.postUserInfoSuccess, 'postUserInfoSuccess')
    eventEmitter.off(APIEventEnum.postNewClubApplySuccess, 'postNewClubApplySuccess')
    eventEmitter.off(APIEventEnum.uploadFileSuccess, 'uploadFileSuccess')
    eventEmitter.off(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess')
})
</script>