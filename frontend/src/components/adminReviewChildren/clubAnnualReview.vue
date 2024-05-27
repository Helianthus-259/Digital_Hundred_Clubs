<template>
  <t-aside>
    <t-list stripe="true" style="max-height: 1000px" :scroll="{ type: 'virtual' }">
      <t-list-item v-for="declaration in declarations" style="width: auto" :key="declaration.declarationId">
        <t-list-item-meta :title = "declaration.clubName" :description="declaration.declarationYear"/>
        <template #action>
          <t-button
              shape="round"
              :theme="theme[choose === declaration.declarationId ? 1 : 0]"
              @click="detail(declaration.declarationId)"
          >
            <template #icon>
              <ArrowRightIcon/>
            </template>
          </t-button>
        </template>
      </t-list-item>
    </t-list>
  </t-aside>
  <t-layout>
    <t-content>
      <t-descriptions title="学生社团年审表" bordered="" layout="horizontal" item-layout="horizontal" column="2" size="small">
        <t-descriptions-item label="学生社团">{{clubReviewInfo.clubName}}</t-descriptions-item>
        <t-descriptions-item label="学生社团类别">{{clubReviewInfo.clubCategory}}</t-descriptions-item>
        <t-descriptions-item label="业务指导单位">{{clubReviewInfo.responsibleDepartment}}</t-descriptions-item>
        <t-descriptions-item label="学生社团总人数">{{clubReviewInfo.totalMembers}}</t-descriptions-item>
        <t-descriptions-item label="行政指导老师">{{clubReviewInfo.adminGuideTeacher}}</t-descriptions-item>
        <t-descriptions-item label="业务指导老师">{{clubReviewInfo.businessGuideTeacher}}</t-descriptions-item>
        <t-descriptions-item label="学生社团分布校园(区)">
          <t-radio-group variant="primary-filled" :value="clubReviewInfo.mainCompus">
            <t-radio-button value="南校区" disabled>南校区</t-radio-button>
            <t-radio-button value="东校区" disabled>东校区</t-radio-button>
            <t-radio-button value="北校区" disabled>北校区</t-radio-button>
            <t-radio-button value="深圳校区" disabled>深圳校区</t-radio-button>
            <t-radio-button value="珠海校区" disabled>珠海校区</t-radio-button>
          </t-radio-group>
        </t-descriptions-item>
      </t-descriptions>
      <t-descriptions bordered="" layout="horizontal" item-layout="horizontal" column="2" size="small">
        <t-descriptions-item label="学生负责人姓名">{{clubReviewInfo.contactPerson}}</t-descriptions-item>
        <t-descriptions-item label="学生负责人政治面貌">{{clubReviewInfo.politicalStatus}}</t-descriptions-item>
        <t-descriptions-item label="宣传管理情况">
          <t-descriptions bordered="" layout="vertical" size="small">
            <t-descriptions-item label="微信公众号">{{clubReviewInfo.publicityManagementInfo.wechatOfficial}}</t-descriptions-item>
            <t-descriptions-item label="网站">{{clubReviewInfo.publicityManagementInfo.web}}</t-descriptions-item>
            <t-descriptions-item label="微博">{{clubReviewInfo.publicityManagementInfo.weibo}}</t-descriptions-item>
            <t-descriptions-item label="哔哩哔哩">{{clubReviewInfo.publicityManagementInfo.bilibili}}</t-descriptions-item>
            <t-descriptions-item label="抖音">{{clubReviewInfo.publicityManagementInfo.tiktok}}</t-descriptions-item>
            <t-descriptions-item label="微信视频号">{{clubReviewInfo.publicityManagementInfo.wechatVideo}}</t-descriptions-item>
            <t-descriptions-item label="快手">{{clubReviewInfo.publicityManagementInfo.kuaishou}}</t-descriptions-item>
            <t-descriptions-item label="刊物">{{clubReviewInfo.publicityManagementInfo.journal}}</t-descriptions-item>
            <t-descriptions-item label="其他">{{clubReviewInfo.publicityManagementInfo.other}}</t-descriptions-item>
          </t-descriptions>
        </t-descriptions-item>
      </t-descriptions>
      <t-descriptions bordered="" layout="horizontal" item-layout="horizontal" size="small">
        <t-descriptions-item label="财务管理">
          <t-descriptions bordered="" layout="vertical">
            <t-descriptions-item label="是否接受校外赞助">{{clubReviewInfo.file}}</t-descriptions-item>
            <t-descriptions-item label="是否向社团成员进行财务公开">{{clubReviewInfo.isFinancialInformationPublic}}</t-descriptions-item>
          </t-descriptions>
        </t-descriptions-item>
      </t-descriptions>
      <t-descriptions bordered="" layout="vertical" item-layout="horizontal" column="1" size="small">
        <t-descriptions-item label="学生社团章程">{{clubReviewInfo.file}}</t-descriptions-item>
        <t-descriptions-item label="会议及活动清单">
          <t-button>查看详情</t-button>
        </t-descriptions-item>
        <t-descriptions-item label="院系党组织/职能部门意见">
          <t-textarea placeholder="请输入内容" />
        </t-descriptions-item>
      </t-descriptions>
    </t-content>
    <t-footer>
      <t-space direction="horizontal" size="50%" style="margin-left: 15%">
        <t-button theme="danger">不通过年审</t-button>
        <t-button theme="warning">材料不通过</t-button>
        <t-button theme="success">√通过年审</t-button>
      </t-space>
    </t-footer>
  </t-layout>
</template>

<script setup>
import {onUnmounted, ref} from "vue";
import {ArrowDownIcon, ArrowRightIcon} from "tdesign-icons-vue-next";
import eventEmitter from "@/utils/eventEmitter.js";
import {APIEnum, APIEventEnum} from "@/Enum/index.js";


const declarations = ref([])
const choose = ref(-1)
const theme = ["primary", "success"]
const icon = [ArrowDownIcon.stem, ArrowRightIcon.stem]
const clubReviewInfo = ref({
  clubName:'',
  clubCategory:'',
  isFinancialInformationPublic: 0,
  totalMembers:0,
  administrativeGuideTeacherName: '',
  businessGuideTeacherName: '',
  mainCompus: "",
  clubDescription:'',
  contactPerson: '',
  contactPhone: '',
  politicalStatus:'',
  clubStatus:0,
  publicityManagementInfo:{
    wechatOfficial:'',
    web:'',
    weibo:'',
    bilibili:'',
    tiktok:'',
    wechatVideo:'',
    kuaishou:'',
    journal:'',
    other:'',
  },
  file: ''
})

for (let i = 0; i < 20; i++) {
  declarations.value.push({
    declarationId:i,
    clubName:['篮球社','足球社','羽毛球社'][i % 3],
    declarationYear:["2020","2021","2022"][i % 3],
  });
}
const detail = (data) =>{
  console.log(data)
  choose.value = data
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubEvaluateInfo, { value: data })
  eventEmitter.on(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess', (data) => {
    clubReviewInfo.value.clubName = data.clubName
    clubReviewInfo.value.clubCategory = data.clubCategory
    clubReviewInfo.value.mainCompus = data.mainCompus
    clubReviewInfo.value.clubDescription = data.clubDescription
    clubReviewInfo.value.file = data.file
    clubReviewInfo.value.adminGuideTeacher = data.administrativeGuideTeacherName
    clubReviewInfo.value.businessGuideTeacher = data.businessGuideTeacherName
    clubReviewInfo.value.establishmentDate = data.establishmentDate
    clubReviewInfo.value.contactPerson = data.contactPerson
    clubReviewInfo.value.contactPhone = data.contactPhone
    clubReviewInfo.value.clubStatus = data.clubStatus
    clubReviewInfo.value.responsibleDepartment = data.responsibleDepartment
    clubReviewInfo.value.politicalStatus = data.politicalStatus
    clubReviewInfo.value.publicityManagementInfo = data.publicityManagementInfo
    console.log(clubReviewInfo.value)
  })
}

onUnmounted(() => {
  eventEmitter.off(APIEventEnum.request, APIEnum.getClubEvaluateInfo)
  eventEmitter.off(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess')
})

</script>

<style scoped>

</style>