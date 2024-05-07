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

/* 左边部分内容 */
.leftSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    display: flex;
    justify-content: center;
}

.personalInfo {
    width: 60%;
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

    /* 按钮样式 */
    .t-button {
        margin: 5px;
        width: 65%;
        height: 40px;
        font-size: 16px;
        overflow: hidden;
    }
}
</style>

<template>
    <div class="mainBox">
        <div class="leftSide">
            <div class="leftSideContent">
                <div class="personalInfo">
                    <t-cell-group bordered>
                        <t-cell title="姓名" :note="name" />
                        <t-cell title="性别" :note="gender" />
                        <t-cell title="年龄" :note="age" />
                        <t-cell title="学号" :note="studentID" />
                        <t-cell title="学院" :note="faculty" />
                        <t-cell title="邮箱" :note="email" />
                        <t-cell title="手机号" hover style="height: 56px">
                            <template #note>
                                <t-input :maxcharacter="11" v-model="phone" placeholder="请输入手机号" align="right"
                                    :readonly="readOnly" borderless />
                            </template>
                        </t-cell>
                        <t-cell title="爱好" hover style="height: 56px">
                            <template #note>
                                <t-input v-model="hobby" placeholder="有什么爱好呢？" align="right" :readonly="readOnly"
                                    borderless />
                            </template>
                        </t-cell>
                        <t-cell title="特长" hover style="height: 56px">
                            <template #note>
                                <t-input v-model="specialty" placeholder="特长有哪些呢？" align="right" :readonly="readOnly"
                                    borderless />
                            </template>
                        </t-cell>
                    </t-cell-group>
                    <div style="display: flex; justify-content: center; padding: 10px">
                        <t-button v-show="!readOnly" @click="save">保存</t-button>
                    </div>
                </div>
            </div>
        </div>

        <div class="rightSide">
            <div class="rightSideContent">
                <div class="ribbon">
                    <t-button theme="primary" @click="readOnly = !readOnly">编辑信息</t-button>
                    <t-button theme="light" @click="openDialog">我要创建社团！</t-button>
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
                <t-input v-model="newClubFrom.name" placeholder="请输入社团名称" />
            </t-form-item>
            <t-form-item label="社团简介">
                <div style="width: 80%;">
                    <t-textarea v-model="newClubFrom.introduction" name="社团简介" placeholder="请输入社团简介" autosize />
                </div>
            </t-form-item>
            <t-form-item label="社团类型">
                <mySelect v-model="newClubFrom.clubType" :options="clubTypeOptions"></mySelect>
            </t-form-item>
            <t-form-item label="社团负责人">
                <t-input v-model="newClubFrom.leader" placeholder="请输入社团负责人名字" />
            </t-form-item>
            <t-form-item label="负责人电话">
                <t-input v-model="newClubFrom.leaderPhone" placeholder="请输入社团负责人电话" />
            </t-form-item>
            <t-form-item label="社团所在校区">
                <mySelect v-model="newClubFrom.campus" :options="campusOptions"></mySelect>
            </t-form-item>
            <t-form-item label="社团所属院系">
                <t-input v-model="newClubFrom.faculty" placeholder="请输入社团所属院系" />
            </t-form-item>
            <t-form-item label="行政指导老师">
                <t-input v-model="newClubFrom.teacher" placeholder="请输入行政指导老师名字" />
            </t-form-item>
            <t-form-item label="业务指导老师">
                <t-input v-model="newClubFrom.businessTeacher" placeholder="请输入业务指导老师名字" />
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
            <t-button style="margin: 0 10px;" theme="light" size="small" @click="closeDialog">关闭</t-button>
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
    uid: store.state.uid,
    name: '',
    introduction: '',
    clubType: '',
    leader: '',
    leaderPhone: '',
    campus: '',
    faculty: '',
    teacher: '',
    businessTeacher: '',
    material: [],
})

// 获取leader的名字和电话
const getLeaderInfo = () => {
    newClubFrom.leader = user.value.name
    newClubFrom.leaderPhone = user.value.phone
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
    if (newClubFrom.name === '' || newClubFrom.introduction === '' || newClubFrom.clubType === '' || newClubFrom.leader === '' || newClubFrom.leaderPhone === '' || newClubFrom.campus === '' || newClubFrom.faculty === '' || newClubFrom.teacher === '' || newClubFrom.businessTeacher === '' || newClubFrom.material.length === 0) {
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
const phone = ref('')
const hobby = ref('')
const specialty = ref('')
const name = ref('')
const age = ref('')
const gender = ref('')
const studentID = ref('')
const faculty = ref('')
const email = ref('')

const user = ref({})

// 为上面定义的变量赋值
function assignment() {
    phone.value = user.value.phone
    hobby.value = user.value.hobby
    specialty.value = user.value.specialty
    name.value = user.value.name
    age.value = user.value.age + ''
    gender.value = user.value.gender
    studentID.value = user.value.studentID
    faculty.value = user.value.faculty
    email.value = user.value.email
}

if (isEmptyObject(store.state.userInfo)) {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getUserInfo, { uid: store.state.uid })
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
        uid: store.state.uid,
        phone: phone.value,
        hobby: hobby.value,
        specialty: specialty.value
    })
    user.value.phone = phone.value
    user.value.hobby = hobby.value
    user.value.specialty = specialty.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setUserInfo, user.value)
    readOnly.value = !readOnly.value
    getLeaderInfo()
}
</script>