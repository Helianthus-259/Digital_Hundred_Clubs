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
}

.ribbon {
    width: 100%;
    height: 100%;
    padding: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
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
                    <t-row :gutter="[0, 60]">
                        <t-col>
                            <div class="labelBox">姓名：</div>
                            <div class="valueBox">{{ stName }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">性别：</div>
                            <div class="valueBox">{{ gender }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">生日：</div>
                            <div class="valueBox">{{ dateOfBirth }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">年级：</div>
                            <div class="valueBox">{{ grade }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">学院：</div>
                            <div class="valueBox">{{ college }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">电子邮件：</div>
                            <div class="valueBox">{{ email }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">政治面貌：</div>
                            <div class="valueBox">{{ politicalStatus }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">学号：</div>
                            <div class="valueBox">{{ studentNumber }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">联系方式：</div>
                            <div class="valueBox">{{ contact }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">爱好：</div>
                            <div class="valueBox">{{ hobby }}</div>
                        </t-col>
                        <t-col>
                            <div class="labelBox">特长：</div>
                            <div class="valueBox">{{ specialty }}</div>
                        </t-col>
                    </t-row>
                </template>
                <template #footer>
                    <div style="display: flex; justify-content: space-around;">
                        <t-button theme="primary" @click="readOnly = !readOnly" :disabled="!readOnly">编辑信息</t-button>
                        <t-button theme="default" @click="save" :disabled="readOnly">保存</t-button>
                    </div>
                </template>

            </t-card>
        </div>

        <div class="rightSide">
            <div class="rightSideContent">
                <div class="ribbon">
                    <t-button theme="default" @click="openDialog">我要创建社团！</t-button>
                </div>
            </div>
        </div>
    </div>
    <!-- 申请创建社团弹窗 -->
    <myDialog ref="dialogRef">
        <template #header>
            <h2>新建社团申请表</h2>
        </template>
        <t-form labelWidth="150px" colon requiredMark>
            <t-form-item label="社团名称">
                <t-input v-model="newClubFrom.clubName" placeholder="请输入社团名称" />
            </t-form-item>
            <t-form-item label="社团简介">
                <div style="width: 80%;">
                    <t-textarea v-model="newClubFrom.clubDescription" name="社团简介" placeholder="请输入社团简介" autosize />
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
                <t-input v-model="newClubFrom.college" placeholder="请输入社团所属院系" />
            </t-form-item>
            <t-form-item label="行政指导老师">
                <t-input v-model="newClubFrom.administrativeGuideTeacherName" placeholder="请输入行政指导老师名字" />
            </t-form-item>
            <t-form-item label="业务指导老师">
                <t-input v-model="newClubFrom.businessGuideTeacherName" placeholder="请输入业务指导老师名字" />
            </t-form-item>
            <t-form-item label="申请材料">
                <t-upload v-model="newClubFrom.material" :multiple="false" :max="1"
                    :size-limit="{ size: 3000000, unit: 'B' }"
                    accept=".doc,.docx,.docm,.dot,.dotx,.dotm,.xls,.xlsx,.xlsm,.xlt,.xltx,.xltm,.xlsb,.xlam,.pdf"
                    action="//service-bv448zsw-1257786608.gz.apigw.tencentcs.com/api/upload-demo"
                    @validate="onValidate">
                </t-upload>
            </t-form-item>
        </t-form>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" size="small" @click="submitNewClub">发送</t-button>
            <t-button style="margin: 0 10px;" theme="default" size="small" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>

</template>

<script setup>
import { APIEnum, APIEventEnum, StoreEnum, StoreEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { reactive, ref } from 'vue';
import myDialog from '../myDialog.vue';
import mySelect from '../mySelect.vue';

// 弹窗
const dialogRef = ref(null);

const openDialog = () => {
    dialogRef.value.openDialog();
};

const closeDialog = () => {
    dialogRef.value.closeDialog();
};

// 新建社团
const newClubFrom = reactive({
    studentId: store.state.studentId,
    clubName: '',
    clubDescription: '',
    clubCategory: '',
    leader: '',
    leaderPhone: '',
    mainCampus: '',
    college: '',
    administrativeGuideTeacherName: '',
    businessGuideTeacherName: '',
    material: [],
})

// 获取leader的名字和电话
const getLeaderInfo = () => {
    newClubFrom.leader = user.value.stName
    newClubFrom.leaderPhone = user.value.contact
}

// 社团类型
const clubTypeOptions = [
    { label: '学术类', value: '学术类' },
    { label: '体育类', value: '体育类' },
    { label: '艺术类', value: '艺术类' },
    { label: '公益类', value: '公益类' },
    { label: '科技类', value: '科技类' },
    { label: '其他类', value: '其他类' }
]

// 校区
const campusOptions = [
    { label: '广州南校', value: '广州南校' },
    { label: '广州东校', value: '广州东校' },
    { label: '广州北校', value: '广州北校' },
    { label: '珠海校区', value: '珠海校区' },
    { label: '深圳校区', value: '深圳校区' },
]

// 上传文件限制
const onValidate = (context) => {
    if (context.type === 'FILE_OVER_SIZE_LIMIT') {
        Message.warning('文件大小超出上限');
    }
};

// 检验newClubFrom中的数据是否都已填写
const newClubFromValidate = () => {
    if (newClubFrom.clubName === '' || newClubFrom.clubDescription === '' || newClubFrom.clubCategory === '' || newClubFrom.leader === '' || newClubFrom.leaderPhone === '' || newClubFrom.mainCampus === '' || newClubFrom.college === '' || newClubFrom.administrativeGuideTeacherName === '' || newClubFrom.businessGuideTeacherName === '' || newClubFrom.material.length === 0) {
        alert('请将信息填写完整');
        return false;
    }
    return true;
}

const submitNewClub = () => {
    if (newClubFromValidate()) {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postNewClubApply, newClubFrom)
    }
}


// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 展示个人信息
const contact = ref('')
const hobby = ref('')
const specialty = ref('')
const stName = ref('')
const dateOfBirth = ref('')
const gender = ref('')
const studentNumber = ref('')
const college = ref('')
const email = ref('')
const politicalStatus = ref('')
const grade = ref('')

const user = ref({})

// 为上面定义的变量赋值
function assignment() {
    contact.value = user.value.contact
    hobby.value = user.value.hobby
    specialty.value = user.value.specialty
    stName.value = user.value.stName
    dateOfBirth.value = user.value.dateOfBirth
    gender.value = user.value.gender
    studentNumber.value = user.value.studentNumber
    college.value = user.value.college
    email.value = user.value.email
    politicalStatus.value = user.value.politicalStatus
    grade.value = user.value.grade
}

if (isEmptyObject(store.state.userInfo)) {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getUserInfo, { studentId: store.state.studentId })
} else {
    user.value = store.state.userInfo
    assignment()
    getLeaderInfo()
}

eventEmitter.on(APIEventEnum.getUserInfoSuccess, (data) => {
    user.value = data
    assignment()
    getLeaderInfo()
})


// 判断是否为只读模式
const readOnly = ref(true)

// 保存修改后的信息
function save() {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postUserInfo, {
        studentId: store.state.studentId,
        contact: contact.value,
        hobby: hobby.value,
        specialty: specialty.value
    })
    user.value.contact = contact.value
    user.value.hobby = hobby.value
    user.value.specialty = specialty.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setUserInfo, user.value)
    readOnly.value = !readOnly.value
    getLeaderInfo()
}
</script>