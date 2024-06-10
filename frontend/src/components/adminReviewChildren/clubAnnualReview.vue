<style scoped>
.AnnualAuditContainer {
  width: 100%;
  height: 550px;
  background: #ffffff;
  border-radius: 10px;
  overflow-y: auto;
}

/*滚动条样式*/
.AnnualAuditContainer::-webkit-scrollbar {
  width: 8px;
  /* 滚动条宽度 */
}

.AnnualAuditContainer::-webkit-scrollbar-track {
  background-color: #f1f1f1;
  /* 滚动条轨道背景色 */
}

.AnnualAuditContainer::-webkit-scrollbar-thumb {
  background-color: #888;
  /* 滚动条滑块颜色 */
  border-radius: 4px;
  /* 滚动条滑块圆角 */
}

.AnnualAuditContainer::-webkit-scrollbar-thumb:hover {
  background-color: #555;
  /* 鼠标hover时滑块颜色 */
}

.AnnualAuditTitle {
  height: 70px;
  font-size: 24px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
}

.tableContainer {
  width: 80%;
  margin: 0 auto;
}

.txt {
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
}

.t-row {
  width: 100%;
  align-items: center;
}

.t-row#border {
  border: 2px solid #000;
}

.t-row#table {
  border-top: 2px solid #000;
}

.t-col {
  min-height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.t-col#table {
  border-left: 2px solid #000;
}
</style>

<template>
  <t-aside>
    <t-list stripe="true" style="max-height: 600px; width: 250px" :scroll="{ type: 'virtual' }">
      <t-list-item v-for="declaration in declarations" style="width: auto" :key="declaration.declarationId">
        <t-list-item-meta :title="declaration.clubName" :description="declaration.declarationYear" />
        <template #action>
          <t-button shape="round" :theme="theme[choose === declaration.declarationId ? 1 : 0]"
            @click="detail(declaration.declarationId)">
            <template #icon>
              <ArrowRightIcon />
            </template>
          </t-button>
        </template>
      </t-list-item>
    </t-list>
  </t-aside>
  <t-layout>
    <t-content>
      <div class="AnnualAuditContainer">
        <div class="AnnualAuditTitle">学生社团年审申报表</div>
        <div class="tableContainer">
          <t-row id="border">
            <t-row>
              <t-col :span="3">
                <div class="txt">学生社团名称</div>
              </t-col>
              <t-col id="table" :span="3">{{ clubReviewInfo.clubName }}</t-col>
              <t-col id="table" :span="3">
                <div class="txt">学生社团类别</div>
              </t-col>
              <t-col id="table" :span="3">{{ clubReviewInfo.clubCategory }}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">业务指导单位</div>
              </t-col>
              <t-col id="table" :span="3">{{ clubReviewInfo.responsibleDepartment }}</t-col>
              <t-col id="table" :span="3">
                <div class="txt">学生社团总人数</div>
              </t-col>
              <t-col id="table" :span="3">{{ clubReviewInfo.totalMembers }}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">行政指导老师</div>
              </t-col>
              <t-col id="table" :span="3">{{ clubReviewInfo.administrativeGuideTeacherName }}</t-col>
              <t-col id="table" :span="3">
                <div class="txt">业务指导老师</div>
              </t-col>
              <t-col id="table" :span="3">{{ clubReviewInfo.businessGuideTeacherName }}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">学生社团分布校园（区）</div>
              </t-col>
              <t-col id="table" :span="9">
                <t-radio-group variant="outline" :value="clubReviewInfo.mainCompus">
                  <t-radio-button value="广州校区南校园">广州校区南校区</t-radio-button>
                  <t-radio-button value="广州校区北校园">广州校区北校区</t-radio-button>
                  <t-radio-button value="广州校区东校园">广州校区东校区</t-radio-button>
                  <t-radio-button value="深圳校区">深圳校区</t-radio-button>
                  <t-radio-button value="珠海校区">珠海校区</t-radio-button>
                </t-radio-group>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">学生负责人姓名</div>
              </t-col>
              <t-col id="table" :span="3">{{ clubReviewInfo.contactPerson }}</t-col>
              <t-col id="table" :span="3">
                <div class="txt">学生负责人政治面貌</div>
              </t-col>
              <t-col id="table" :span="3">{{ clubReviewInfo.politicalStatus }}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">宣传管理情况</div>
              </t-col>
              <t-col style="display: inline" :span="9">
                <t-row style="border-left: 2px solid #000;">
                  <t-col :span="3">
                    <div class="txt">微信公众号</div>
                  </t-col>
                  <t-col style="display: block;" :span="9">
                    <t-row style="border-left: 2px solid #000;">
                      <t-col :span="3">
                        <div class="txt">名称</div>
                      </t-col>
                      <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.WeChatPublicAccount.name
                        }}</t-col>
                    </t-row>
                    <t-row style="border-left: 2px solid #000;" id="table">
                      <t-col :span="3">
                        <div class="txt">推送条数</div>
                      </t-col>
                      <t-col id="table" :span="9">{{
                        clubReviewInfo.publicityManagementInfo.WeChatPublicAccount.submitCount }}</t-col>
                    </t-row>
                    <t-row style="border-left: 2px solid #000;" id="table">
                      <t-col :span="3">
                        <div class="txt">三级审核人员</div>
                      </t-col>
                      <t-col style="display: block;" id="table" :span="9">
                        <t-row>
                          <t-col :span="4">
                            <div class="txt">初审</div>
                          </t-col>
                          <t-col id="table" :span="4">
                            <div class="txt">复审</div>
                          </t-col>
                          <t-col id="table" :span="4">
                            <div class="txt">终审</div>
                          </t-col>
                        </t-row>
                        <t-row id="table">
                          <t-col :span="4">{{ clubReviewInfo.publicityManagementInfo.WeChatPublicAccount.Auditors.name1
                            }}</t-col>
                          <t-col id="table" :span="4">{{
                            clubReviewInfo.publicityManagementInfo.WeChatPublicAccount.Auditors.name2 }}</t-col>
                          <t-col id="table" :span="4">{{
                            clubReviewInfo.publicityManagementInfo.WeChatPublicAccount.Auditors.name3 }}</t-col>
                        </t-row>
                      </t-col>
                    </t-row>
                  </t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="3">
                    <div class="txt">网站</div>
                  </t-col>
                  <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.WebSite.name }}</t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="3">
                    <div class="txt">微博</div>
                  </t-col>
                  <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.Blog.name }}</t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="3">
                    <div class="txt">哔哩哔哩</div>
                  </t-col>
                  <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.BiliBili.name }}</t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="3">
                    <div class="txt">抖音</div>
                  </t-col>
                  <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.TikTok.name }}</t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="3">
                    <div class="txt">微信视频号</div>
                  </t-col>
                  <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.WeChatVideoAccount.name
                    }}</t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="3">
                    <div class="txt">快手</div>
                  </t-col>
                  <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.KuaiShou.name }}</t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="3">
                    <div class="txt">刊物</div>
                  </t-col>
                  <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.Publication.name }}</t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="3">
                    <div class="txt">其他</div>
                  </t-col>
                  <t-col id="table" :span="9">{{ clubReviewInfo.publicityManagementInfo.Other.name }}</t-col>
                </t-row>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">财务管理</div>
              </t-col>
              <t-col style="display: inline" :span="9">
                <t-row>
                  <t-col id="table" :span="8">
                    <div class="txt">是否接受校外赞助</div>
                  </t-col>
                  <t-col id="table" :span="4">{{ clubReviewInfo.externalsponsorshipAttachment }}</t-col>
                </t-row>
                <t-row id="table">
                  <t-col id="table" :span="8">
                    <div class="txt">是否向社团成员进行财务公开</div>
                  </t-col>
                  <t-col id="table" :span="4">{{ clubReviewInfo.isFinancialInformationPublic}}</t-col>
                </t-row>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">学生社团章程</div>
              </t-col>
              <t-col id="table" :span="9">{{ clubReviewInfo.clubConstitutionAttachment }}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">会议及活动清单</div>
              </t-col>
              <t-col id="table" :span="9">{{ clubReviewInfo.meetingActivityListAttachment }}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="txt">院系党组织/职能部门意见</div>
              </t-col>
              <t-col id="table" :span="9">
                <t-textarea
                    v-model="departmentOpinion"
                    placeholder="请输入意见"
                    name="description"
                    :autosize="true"
                />
              </t-col>
            </t-row>
          </t-row>
        </div>
      </div>
      <t-space direction="horizontal" size="50%" style="margin-left: 30%; height: 50px">
        <t-button theme="danger" style="margin-top: 10px" @click="unPassClubAnnualReview">材料不通过</t-button>
        <t-button theme="success" style="margin-top: 10px" @click="passClubAnnualReview">√通过年审</t-button>
      </t-space>
    </t-content>
  </t-layout>
</template>

<script setup>
import { onUnmounted, ref } from "vue";
import { ArrowDownIcon, ArrowRightIcon } from "tdesign-icons-vue-next";
import eventEmitter from "@/utils/eventEmitter.js";
import { APIEnum, APIEventEnum } from "@/Enum/index.js";
import {NotifyPlugin} from "tdesign-vue-next";

const departmentOpinion = ref("")
const declarations = ref([])
const choose = ref(-1)
const theme = ["primary", "success"]
const icon = [ArrowDownIcon.stem, ArrowRightIcon.stem]
const clubReviewInfo = ref({
  declarationId: null,
  clubName: '',
  clubCategory: '',
  mainCompus: '',
  clubDescription: '',
  totalMembers: '',
  file: '',
  administrativeGuideTeacherName: '',
  businessGuideTeacherName: '',
  establishmentDate: '',
  contactPerson: '',
  contactPhone: '',
  clubStatus: '',
  responsibleDepartment: '',
  politicalStatus: '',
  isFinancialInformationPublic: '',
  publicityManagementInfo: {
    WeChatPublicAccount: { name: '', submitCount: '', Auditors: { name1: '', name2: '', name3: '' } },
    WebSite: { has: '', name: '' },
    Blog: { has: '', name: '' },
    BiliBili: { has: '', name: '' },
    TikTok: { has: '', name: '' },
    WeChatVideoAccount: { has: '', name: '' },
    KuaiShou: { has: '', name: '' },
    Publication: { has: '', name: '' },
    Other: { has: '', name: '' },
  },
  meetingActivityListAttachment: '',
  externalsponsorshipAttachment:'',
  clubConstitutionAttachment:'',
})

eventEmitter.emit(APIEventEnum.request, APIEnum.getClubAnnuals)
eventEmitter.on(APIEventEnum.getClubAnnualsSuccess, 'getClubAnnualsSuccess', (data)=>{
  declarations.value = data
})

const detail = (data) => {
  console.log(data)
  choose.value = data
  clubReviewInfo.value.declarationId = data
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubAnnual, data )
  eventEmitter.on(APIEventEnum.getClubAnnualSuccess, 'getClubAnnualSuccess', (data) => {
    console.log(data)
    clubReviewInfo.value.clubName = data.clubName
    clubReviewInfo.value.clubCategory = JSON.parse(localStorage.getItem('enumList')).clubCategories[data.clubCategory].name
    clubReviewInfo.value.mainCompus = JSON.parse(localStorage.getItem('enumList')).mainCampuses[data.mainCampus].name
    clubReviewInfo.value.clubDescription = data.clubDescription
    clubReviewInfo.value.file = data.file
    clubReviewInfo.value.totalMembers = data.totalMembers
    clubReviewInfo.value.administrativeGuideTeacherName = data.administrativeGuideTeacherName
    clubReviewInfo.value.businessGuideTeacherName = data.businessGuideTeacherName
    clubReviewInfo.value.establishmentDate = data.establishmentDate
    clubReviewInfo.value.contactPerson = data.contactPerson
    clubReviewInfo.value.contactPhone = data.contactPhone
    clubReviewInfo.value.clubStatus = data.clubStatus
    clubReviewInfo.value.responsibleDepartment = data.responsibleDepartment
    clubReviewInfo.value.politicalStatus = data.politicalStatus
    clubReviewInfo.value.publicityManagementInfo = data.publicityManagementInfo
    clubReviewInfo.value.isFinancialInformationPublic = data.isFinancialInformationPublic === '0' ? '否' : '是'
    clubReviewInfo.value.meetingActivityListAttachment = data.meetingActivityListAttachment
    clubReviewInfo.value.externalsponsorshipAttachment = data.externalsponsorshipAttachment
    clubReviewInfo.value.clubConstitutionAttachment = data.clubConstitutionAttachment
    console.log(clubReviewInfo.value)
  })
}

const passClubAnnualReview = () => {
  if(clubReviewInfo.value.declarationId === null){
    NotifyPlugin.warning({
      title: '操作失败',
      content: '请选择任一社团年审数据！',
    })
    return
  }
  eventEmitter.emit(APIEventEnum.request, APIEnum.passClubAnnualReview, {
    declarationId: clubReviewInfo.value.declarationId,
    departmentOpinion: departmentOpinion.value
  })
}

const unPassClubAnnualReview = () => {
  if(clubReviewInfo.value.declarationId === null){
    NotifyPlugin.warning({
      title: '操作失败',
      content: '请选择任一社团年审数据！',
    })
    return
  }
  eventEmitter.emit(APIEventEnum.request, APIEnum.unPassClubAnnualReview, {
    declarationId: clubReviewInfo.value.declarationId,
    departmentOpinion:departmentOpinion.value
  })
}

eventEmitter.on(APIEventEnum.passClubAnnualReviewSuccess, 'passClubAnnualReviewSuccess', ()=>{
  NotifyPlugin.success({
    title: '操作成功',
    content: '通过社团年审成功',
  })
})

eventEmitter.on(APIEventEnum.unPassClubAnnualReviewSuccess, 'unPassClubAnnualReviewSuccess', ()=>{
  NotifyPlugin.info({
    title: '操作成功',
    content: '驳回社团年审成功',
  })
})


onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess')
  eventEmitter.off(APIEventEnum.getClubAnnualSuccess, 'getClubAnnualSuccess')
  eventEmitter.off(APIEventEnum.passClubAnnualReviewSuccess, 'passClubAnnualReviewSuccess')
  eventEmitter.off(APIEventEnum.unPassClubAnnualReviewSuccess, 'unPassClubAnnualReviewSuccess')
})

</script>
