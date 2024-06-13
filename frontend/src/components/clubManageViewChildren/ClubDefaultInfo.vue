<style scoped>
.defaultInfoBox {
    width: 100%;
    height: 690px;
}

.upBox {
    width: 100%;
    height: 50%;
    background: #efefef;
    display: flex;
}

.downBox {
    width: 100%;
    height: 50%;
    background: #ffffff;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.upLeftBox {
    width: 50%;
    height: 100%;
    border-bottom: 10px solid #efefef;
    border-bottom-right-radius: 10px;
    border-right: 10px solid #efefef;
}

.avatarBox {
    width: 100%;
    height: 100%;
    background: #ffffff;
    border-radius: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

.avatarBox img {
    width: 95%;
    height: 95%;
    border-radius: 10px;
}

.upRightBox {
    width: 50%;
    height: 100%;
    background: #ffffff;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    overflow: auto;
}

.upRightBox .t-row {
    width: 100%;
}

.t-col {
    display: flex;
    justify-content: center;
    align-items: center;

    .contentBox {
        width: 90%;
        min-height: 32px;
        display: flex;
        justify-content: center;
        align-items: center;
        background: #f5f5f5;
        border-radius: 5px;
        cursor: pointer;

        .labelBox {
            width: 40%;
            display: inline-flex;
            justify-content: center;
            align-items: center;
            font-size: 16px;
            font-weight: bold;
        }

        .valueBox {
            width: 60%;
            display: inline-flex;
            justify-content: left;
            align-items: center;
        }
    }

    .contentBox:hover {
        background: #efefef;
    }
}
</style>

<template>
    <div class="defaultInfoBox">
        <div class="upBox">
            <div class="upLeftBox">
                <div class="avatarBox">
                    <div
                        style="height: 10%; width: 100%; padding-left: 30px; padding-top: 10px; font-size: 16px; font-weight: bold;">
                        社团封面:</div>
                    <div style="height: 90%; width: 100%;">
                        <div
                            style="height: 100%; width: 100%; display: flex; justify-content: center; align-items: center;">
                            <img v-show="!isEditting" :src="clubInfo.imageUrl">
                            <t-upload v-show="isEditting" :image-viewer-props="{ closeOnEscKeydown: false }"
                                :size-limit="{ size: 500, unit: 'KB' }" theme="image" accept="image/*"
                                :auto-upload="false" :show-image-file-name="true" :locale="{
                                    triggerUploadText: {
                                        image: '请选择图片',
                                    },
                                }" :onSelectChange="selectChangeHandler">
                            </t-upload>
                        </div>
                    </div>
                </div>
            </div>
            <div class="upRightBox">
                <t-row style="width: 100%;" :gutter="[0, 30]">
                    <t-col v-for="(item, index) in clubMember" :key="index" :span="6">
                        <div class="contentBox">
                            <div class="labelBox">{{ item.position }}：</div>
                            <div class="valueBox">
                                <div style="width: 40%;">{{ item.stName }}</div>
                                <div style="width: 60%;">
                                    <t-button @click="updateExecutive(index, item)" variant="text" shape="square">
                                        <t-popup content="更换" placement="top" show-arrow>
                                            <t-icon name="rotate" />
                                        </t-popup>
                                    </t-button>
                                    <t-button :disabled="item.position === '社长'" @click="deleteExecutive(index, item)"
                                        variant="text" shape="square">
                                        <t-popup :content="item.position === '社长' ? '社长不能撤除' : '撤除'" placement="top"
                                            show-arrow>
                                            <t-icon name="delete" />
                                        </t-popup>
                                    </t-button>
                                </div>
                            </div>
                        </div>
                    </t-col>
                    <t-col :span="6">
                        <div class="contentBox" @click="addExecutive">
                            <t-popup content="添加干部" placement="top" show-arrow>
                                <t-icon name="add" />
                            </t-popup>
                        </div>
                    </t-col>
                </t-row>
            </div>
        </div>
        <div class="downBox">
            <t-row style="width: 100%;" :gutter="[0, 60]">
                <t-col :span="3">
                    <div class="labelBox">社团名称：</div>
                    <div class="valueBox">
                        <t-input borderless v-model="clubInfo.clubName" :readonly="!isEditting" />
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">成立时间：</div>
                    <div class="valueBox">
                        {{ clubInfo.establishmentDate }}
                        <!-- <t-input borderless v-model="clubInfo.establishmentDate" :readonly="!isEditting" /> -->
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">社团类别：</div>
                    <div class="valueBox">
                        <t-select v-model="clubInfo.clubCategory" borderless :readonly="!isEditting" showArrow>
                            <t-option v-for="(item, index) in clubCategories" :key="index" :label="item.name"
                                :value="item.code" />
                        </t-select>
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">业务指导单位：</div>
                    <div class="valueBox">
                        {{ clubInfo.responsibleDepartment }}
                        <!-- <t-select v-model="clubInfo.responsibleDepartment" borderless :readonly="!isEditting" showArrow>
                            <t-option label="体育部" value="体育部" />
                            <t-option label="艺术部" value="艺术部" />
                            <t-option label="学术部" value="学术部" />
                            <t-option label="公益部" value="公益部" />
                        </t-select> -->
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">行政指导老师：</div>
                    <div class="valueBox">
                        <t-input borderless v-model="clubInfo.administrativeGuideTeacherName" :readonly="!isEditting" />
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">业务指导老师：</div>
                    <div class="valueBox">
                        <t-input borderless v-model="clubInfo.businessGuideTeacherName" :readonly="!isEditting" />
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">主校区：</div>
                    <div class="valueBox">
                        <t-select v-model="clubInfo.mainCompus" borderless :readonly="!isEditting" showArrow>
                            <t-option v-for="(item, index) in mainCampuses" :key="index" :label="item.name"
                                :value="item.code" />
                        </t-select>
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">社团人数：</div>
                    <div class="valueBox">
                        {{ clubInfo.totalMembers }}
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">财务公开：</div>
                    <div class="valueBox">
                        <t-radio-group v-model="clubInfo.isFinancialInformationPublic">
                            <t-radio :readonly="!isEditting" value="0">否</t-radio>
                            <t-radio :readonly="!isEditting" value="1">是</t-radio>
                        </t-radio-group>
                    </div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">
                        <t-button :disabled="isEditting" theme="primary" variant="outline"
                            @click="editInfo">编辑信息</t-button>
                    </div>
                    <div class="valueBox"></div>
                </t-col>
                <t-col :span="3">
                    <div class="labelBox">
                        <t-button :disabled="!isEditting" theme="default" variant="outline"
                            @click="saveEdit">保存编辑</t-button>
                    </div>
                    <div class="valueBox"></div>
                </t-col>
            </t-row>
        </div>
    </div>

    <!-- 确认删除弹框 -->
    <myDialog ref="deleteDialogRef">
        <template #header>
            <div style="width: 100%; font-size: 24px; font-weight: bold">
                <t-button shape="circle" theme="warning" variant="text">
                    <template #icon>
                        <t-icon size="60px" name="error-circle-filled" />
                    </template>
                </t-button>
                警告
            </div>
        </template>
        <p>确定要撤除<span style="font-size: 24px">{{ deleteStudent.stName }}</span>的<span style="font-size: 24px">{{
            deleteStudent.position }}</span>职务吗？</p>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="confirmDelete">确定</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeDeleteDialog">关闭</t-button>
        </template>
    </myDialog>

    <myDialog ref="addDialogRef">
        <template #header>
            <div style="font-size: 24px; font-weight: bold">请填写社团干部信息</div>
        </template>
        <div style="width: 100%; height: 250px;  display: flex;  justify-content: center; align-items: center;">
            <t-form colon>
                <t-form-item label="名字">
                    <t-input v-model="newStudent.stName"></t-input>
                </t-form-item>
                <t-form-item label="学号">
                    <t-input v-model="newStudent.studentNumber"></t-input>
                </t-form-item>
                <t-form-item label="职务">
                    <t-select v-model="newStudent.position" showArrow>
                        <t-option label="副社长" value="副社长" />
                        <t-option label="干部" value="干部" />
                    </t-select>
                </t-form-item>
            </t-form>
        </div>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="confirmAdd">添加</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeAddDialog">关闭</t-button>
        </template>
    </myDialog>

    <myDialog ref="updateDialogRef">
        <template #header>
            <div style="font-size: 24px; font-weight: bold">请填写新社团干部信息</div>
        </template>
        <div style="width: 100%; height: 250px;  display: flex;  justify-content: center; align-items: center;">
            <t-form colon>
                <t-form-item label="名字">
                    <t-input v-model="newStudent.stName"></t-input>
                </t-form-item>
                <t-form-item label="学号">
                    <t-input v-model="newStudent.studentNumber"></t-input>
                </t-form-item>
            </t-form>
        </div>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="confirmUpdate">更换</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeUpdateDialog">关闭</t-button>
        </template>
    </myDialog>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import eventEmitter from '@/utils/eventEmitter';
import { MessagePlugin } from 'tdesign-vue-next';
import { onMounted, onUnmounted, reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
import myDialog from '../myDialog.vue';
import moment from "moment/moment.js";
import formatDate from '@/utils';

const route = useRoute();
const clubId = route.params.cid

// 校区枚举
const mainCampuses = JSON.parse(localStorage.getItem('enumList')).mainCampuses

// 社团类型枚举
const clubCategories = JSON.parse(localStorage.getItem('enumList')).clubCategories

// 职位枚举
const positions = JSON.parse(localStorage.getItem('enumList')).positions

// 社团信息
const clubInfo = reactive({
    clubName: '',
    establishmentDate: '',
    clubCategory: '',
    responsibleDepartment: '',
    administrativeGuideTeacherName: '',
    businessGuideTeacherName: '',
    mainCompus: '',
    totalMembers: '',
    isFinancialInformationPublic: '',
    imageUrl: '',
})

// 社团干部
const clubMember = reactive([{ studentNumber: '', stName: '', position: '' }])

// 控制删除弹窗
const deleteStudent = {
    studentNumber: '',
    stName: '',
    position: ''
}
let deleteIndex = -1
const deleteDialogRef = ref(null)
const closeDeleteDialog = () => {
    deleteDialogRef.value.closeDialog()
}
const openDeleteDialog = () => {
    deleteDialogRef.value.openDialog()
}
const confirmDelete = () => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postDeleteClubMember, { clubId, studentNumber: deleteStudent.studentNumber })
}

// 控制添加弹窗
const addDialogRef = ref(null)
const newStudent = reactive({
    studentNumber: '',
    stName: '',
    position: '',
})

const newStudentValidate = () => {
    if (newStudent.studentNumber === '' || newStudent.stName === '' || newStudent.position === '') {
        MessagePlugin.warning('请填写完整信息')
        return false
    }
    return true
}
const closeAddDialog = () => {
    addDialogRef.value.closeDialog()
}
const openAddDialog = () => {
    addDialogRef.value.openDialog()
}
const confirmAdd = () => {
    if (newStudentValidate())
        eventEmitter.emit(APIEventEnum.request, APIEnum.postAddClubMember, {
            clubId,
            studentNumber: newStudent.studentNumber,
            stName: newStudent.stName,
            position: positions.filter(item => item.name === newStudent.position)[0].code
        })
}

// 控制更新弹窗
const updateDialogRef = ref(null)
const oldStudent = {
    studentNumber: '',
    stName: '',
    position: '',
}
let updateIndex = -1;
const closeUpdateDialog = () => {
    updateDialogRef.value.closeDialog()
}
const openUpdateDialog = () => {
    updateDialogRef.value.openDialog()
}
const confirmUpdate = () => {
    if (newStudentValidate())
        eventEmitter.emit(APIEventEnum.request, APIEnum.postUpdateClubMember, {
            clubId,
            oldStudent: {
                studentNumber: oldStudent.studentNumber,
                stName: oldStudent.stName,
                position: positions.filter(item => item.name === oldStudent.position)[0].code
            },
            newStudent: {
                studentNumber: newStudent.studentNumber,
                stName: newStudent.stName,
                position: positions.filter(item => item.name === newStudent.position)[0].code
            }
        })
}


const isEditting = ref(false)

const oldClubInfo = {
    clubName: '',
    establishmentDate: '',
    clubCategory: '',
    responsibleDepartment: '',
    administrativeGuideTeacherName: '',
    businessGuideTeacherName: '',
    mainCompus: '',
    totalMembers: '',
    isFinancialInformationPublic: '',
    imageUrl: '',
}
const editInfo = () => {
    isEditting.value = true
    oldClubInfo.clubName = clubInfo.clubName
    oldClubInfo.establishmentDate = clubInfo.establishmentDate
    oldClubInfo.clubCategory = clubInfo.clubCategory
    oldClubInfo.responsibleDepartment = clubInfo.responsibleDepartment
    oldClubInfo.administrativeGuideTeacherName = clubInfo.administrativeGuideTeacherName
    oldClubInfo.businessGuideTeacherName = clubInfo.businessGuideTeacherName
    oldClubInfo.mainCompus = clubInfo.mainCompus
    oldClubInfo.totalMembers = clubInfo.totalMembers
    oldClubInfo.isFinancialInformationPublic = clubInfo.isFinancialInformationPublic
    oldClubInfo.imageUrl = clubInfo.imageUrl
    MessagePlugin.success('启用编辑')
}

const saveEdit = () => {
    if (clubInfo.clubName === oldClubInfo.clubName &&
        clubInfo.establishmentDate === oldClubInfo.establishmentDate &&
        clubInfo.clubCategory === oldClubInfo.clubCategory &&
        clubInfo.responsibleDepartment === oldClubInfo.responsibleDepartment &&
        clubInfo.administrativeGuideTeacherName === oldClubInfo.administrativeGuideTeacherName &&
        clubInfo.businessGuideTeacherName === oldClubInfo.businessGuideTeacherName &&
        clubInfo.mainCompus === oldClubInfo.mainCompus &&
        clubInfo.totalMembers === oldClubInfo.totalMembers &&
        clubInfo.isFinancialInformationPublic === oldClubInfo.isFinancialInformationPublic &&
        clubInfo.imageUrl === oldClubInfo.imageUrl) {
        isEditting.value = false
        MessagePlugin.success('保存成功')
    } else {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postUpdateClubInfo, { clubId, ...clubInfo })
    }
}

const selectChangeHandler = (fileList) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadImage, { file: fileList[0] })
}

const addExecutive = () => {
    openAddDialog()
}

const updateExecutive = (index, item) => {
    openUpdateDialog()
    oldStudent.stName = item.stName
    oldStudent.studentNumber = item.studentNumber
    oldStudent.position = item.position
    newStudent.position = item.position
    updateIndex = index
}

const deleteExecutive = (index, item) => {
    openDeleteDialog()
    deleteStudent.studentNumber = item.studentNumber
    deleteStudent.stName = item.stName
    deleteStudent.position = item.position
    deleteIndex = index
}

onMounted(() => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getClubInfo, { clubId })
    eventEmitter.emit(APIEventEnum.request, APIEnum.getClubMembers, { clubId })
    eventEmitter.on(APIEventEnum.getClubInfoSuccess, 'getClubInfoSuccess', (data) => {
        console.log(data)
        clubInfo.clubName = data.clubName
        clubInfo.establishmentDate = formatDate(new Date(data.establishmentDate), 'yyyy-MM-dd hh:mm:ss')
        clubInfo.clubCategory = +data.clubCategory
        clubInfo.responsibleDepartment = +data.responsibleDepartment
        clubInfo.administrativeGuideTeacherName = data.administrativeGuideTeacherName
        clubInfo.businessGuideTeacherName = data.businessGuideTeacherName
        clubInfo.mainCompus = JSON.parse(localStorage.getItem('enumList')).mainCampuses[data.mainCompus].name
        clubInfo.totalMembers = data.totalMembers
        clubInfo.isFinancialInformationPublic = data.isFinancialInformationPublic + ''
        clubInfo.imageUrl = '/src/assets/loginBg.jpg'
    })
    eventEmitter.on(APIEventEnum.getClubMembersSuccess, 'getClubMembersSuccess', (data) => {
        clubMember[0].stName = data.president.stName;
        clubMember[0].studentNumber = data.president.studentNumber;
        clubMember[0].position = positions[data.president.position].name;
        data.executives.forEach(item => {
            clubMember.push({
                studentNumber: item.studentNumber,
                stName: item.stName,
                position: positions[item.position].name
            })
        });

    })
    eventEmitter.on(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess', (data) => {
        clubInfo.imageUrl = data.url
        console.log(clubInfo.imageUrl);
    })
    eventEmitter.on(APIEventEnum.postDeleteClubMemberSuccess, 'postDeleteClubMemberSuccess', () => {
        clubMember.splice(deleteIndex, 1)
        MessagePlugin.success('撤除成功')
        closeDeleteDialog()
    })
    eventEmitter.on(APIEventEnum.postUpdateClubMemberSuccess, 'postUpdateClubMemberSuccess', () => {
        clubMember[updateIndex].stName = newStudent.stName
        clubMember[updateIndex].studentNumber = newStudent.studentNumber
        clubMember[updateIndex].position = newStudent.position
        newStudent.stName = ''
        newStudent.studentNumber = ''
        newStudent.position = ''
        MessagePlugin.success('更新成功')
        closeUpdateDialog()
    })
    eventEmitter.on(APIEventEnum.postAddClubMemberSuccess, 'postAddClubMemberSuccess', () => {
        clubMember.push({
            studentNumber: newStudent.studentNumber,
            stName: newStudent.stName,
            position: newStudent.position
        })
        newStudent.stName = ''
        newStudent.studentNumber = ''
        newStudent.position = ''
        MessagePlugin.success('添加成功')
        closeAddDialog()
    })
    eventEmitter.on(APIEventEnum.postUpdateClubInfoSuccess, 'postUpdateClubInfoSuccess', () => {
        isEditting.value = false
        MessagePlugin.success('更新社团信息成功')
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess')
    eventEmitter.off(APIEventEnum.getClubMembersSuccess, 'getClubMembersSuccess')
    eventEmitter.off(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess')
    eventEmitter.off(APIEventEnum.postDeleteClubMemberSuccess, 'postDeleteClubMemberSuccess')
})
</script>