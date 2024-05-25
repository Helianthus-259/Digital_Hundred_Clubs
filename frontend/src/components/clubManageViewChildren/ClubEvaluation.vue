<style scoped>
.clubEvaluationContainer {
    width: 100%;
    height: 690px;
    background: #ffffff;
    border-radius: 10px;
    overflow-y: auto;
}

/*滚动条样式*/
.clubEvaluationContainer::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.clubEvaluationContainer::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
}

.clubEvaluationContainer::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.clubEvaluationContainer::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

.titleContainer {
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

.text {
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

.t-row#tableBorder {
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

.attachContainer {
    width: 80%;
    margin: 0 auto 10px;
    margin-top: 100px;
}
</style>

<template>
    <div class="clubEvaluationContainer">
        <div class="titleContainer">优秀学生社团申请表</div>
        <div class="tableContainer">
            <t-row id="tableBorder">
                <t-row>
                    <t-col :span="3">
                        <div class="text">学生社团名称</div>
                    </t-col>
                    <t-col :span="9" id="table"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">学生社团总人数</div>
                    </t-col>
                    <t-col :span="3" id="table"></t-col>
                    <t-col :span="3" id="table">
                        <div class="text">学生社团骨干人数</div>
                    </t-col>
                    <t-col :span="3" id="table"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="9">
                        <div class="text">学生骨干是中共党员、入党积极分子或提交入党申请书人数</div>
                    </t-col>
                    <t-col :span="3" id="table"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">行政指导老师</div>
                    </t-col>
                    <t-col :span="3" id="table"></t-col>
                    <t-col :span="3" id="table">
                        <div class="text">业务指导老师</div>
                    </t-col>
                    <t-col :span="3" id="table"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">换届情况</div>
                    </t-col>
                    <t-col style="display: inline;" :span="3" id="table">
                        <t-row>
                            <t-col :span="12">
                                <div class="text">换届方式</div>
                            </t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col :span="12">
                                <div class="text">参与人数</div>
                            </t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col :span="12">
                                <div class="text">指导教师是否参与指导</div>
                            </t-col>
                        </t-row>
                    </t-col>
                    <t-col style="display: inline;" :span="6" id="table">
                        <t-row><t-col :span="12"></t-col></t-row>
                        <t-row id="table"><t-col :span="12"></t-col></t-row>
                        <t-row id="table"><t-col :span="12"></t-col></t-row>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">财务管理</div>
                    </t-col>
                    <t-col :span="9">
                        <t-row style="border-left: 2px solid #000;">
                            <t-col :span="8">
                                <div class="text">是否向社团成员进行财务公开</div>
                            </t-col>
                            <t-col :span="4" id="table"></t-col>
                        </t-row>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">全员大会、骨干例会情况</div>
                    </t-col>
                    <t-col style="display: inline;" :span="9">
                        <t-row style="border-left: 2px solid #000;">
                            <t-col :span="4">
                                <div class="text">时间</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <div class="text">地点/线上平台</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <div class="text">全员大会/骨干例会</div>
                            </t-col>
                        </t-row>
                        <div v-for="(item, index) in 1">
                            <t-popup placement="left-bottom">
                                <template #content>
                                    <t-button shape="square" variant="text">
                                        <t-icon name="add" />
                                    </t-button>
                                    <t-button shape="square" variant="text">
                                        <t-icon name="delete" />
                                    </t-button>
                                </template>
                                <t-row style="border-left: 2px solid #000;" id="table">
                                    <t-col :span="4">
                                    </t-col>
                                    <t-col id="table" :span="4">
                                    </t-col>
                                    <t-col id="table" :span="4">
                                    </t-col>
                                </t-row>
                            </t-popup>
                        </div>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">社团获奖情况（只填写市级、省级、国家级荣誉）</div>
                    </t-col>
                    <t-col style="display: inline;" :span="9">
                        <t-row style="border-left: 2px solid #000;">
                            <t-col :span="4">
                                <div class="text">名称</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <div class="text">时间</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <div class="text">颁发单位</div>
                            </t-col>
                        </t-row>
                        <div v-for="(item, index) in 1">
                            <t-popup placement="left-bottom">
                                <template #content>
                                    <t-button shape="square" variant="text">
                                        <t-icon name="add" />
                                    </t-button>
                                    <t-button shape="square" variant="text">
                                        <t-icon name="delete" />
                                    </t-button>
                                </template>
                                <t-row style="border-left: 2px solid #000;" id="table">
                                    <t-col :span="4">
                                    </t-col>
                                    <t-col id="table" :span="4">
                                    </t-col>
                                    <t-col id="table" :span="4">
                                    </t-col>
                                </t-row>
                            </t-popup>
                        </div>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">宣传管理成效</div>
                    </t-col>
                    <t-col style="display: inline;" :span="9">
                        <t-row style="border-left: 2px solid #000;">
                            <t-col :span="8">
                                <div class="text">向学校平台投稿次数</div>
                            </t-col>
                            <t-col id="table" :span="4"></t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="4">
                                <div class="text">获校级以上宣传情况</div>
                            </t-col>
                            <t-col style="display: inline;" :span="8">
                                <t-row>
                                    <t-col id="table" :span="6">
                                        <div class="text">宣传平台</div>
                                    </t-col>
                                    <t-col id="table" :span="6">
                                        <div class="text">报道内容</div>
                                    </t-col>
                                </t-row>
                                <div v-for="(item, index) in 2">
                                    <t-popup placement="left-bottom">
                                        <template #content>
                                            <t-button shape="square" variant="text">
                                                <t-icon name="add" />
                                            </t-button>
                                            <t-button shape="square" variant="text">
                                                <t-icon name="delete" />
                                            </t-button>
                                        </template>
                                        <t-row id="table">
                                            <t-col id="table" :span="6">
                                            </t-col>
                                            <t-col id="table" :span="6">
                                            </t-col>
                                        </t-row>
                                    </t-popup>
                                </div>
                            </t-col>
                        </t-row>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">学生社团承办校级以上活动主办方及活动名称</div>
                    </t-col>
                    <t-col style="display: inline;" :span="9">
                        <t-row style="border-left: 2px solid #000;">
                            <t-col :span="4">
                                <div class="text">类别</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <div class="text">主办方</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <div class="text">活动名称</div>
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="4">
                                <div class="text">校级</div>
                            </t-col>
                            <t-col style="display: inline;" :span="8">
                                <div v-for="(item, index) in 2">
                                    <t-popup placement="left-bottom">
                                        <template #content>
                                            <t-button shape="square" variant="text">
                                                <t-icon name="add" />
                                            </t-button>
                                            <t-button shape="square" variant="text">
                                                <t-icon name="delete" />
                                            </t-button>
                                        </template>
                                        <t-row :id="index === 0 ? '' : 'table'">
                                            <t-col id="table" :span="6">
                                            </t-col>
                                            <t-col id="table" :span="6">
                                            </t-col>
                                        </t-row>
                                    </t-popup>
                                </div>
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="4">
                                <div class="text">市级</div>
                            </t-col>
                            <t-col style="display: inline;" :span="8">
                                <div v-for="(item, index) in 1">
                                    <t-popup placement="left-bottom">
                                        <template #content>
                                            <t-button shape="square" variant="text">
                                                <t-icon name="add" />
                                            </t-button>
                                            <t-button shape="square" variant="text">
                                                <t-icon name="delete" />
                                            </t-button>
                                        </template>
                                        <t-row :id="index === 0 ? '' : 'table'">
                                            <t-col id="table" :span="6">
                                            </t-col>
                                            <t-col id="table" :span="6">
                                            </t-col>
                                        </t-row>
                                    </t-popup>
                                </div>
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="4">
                                <div class="text">省级及以上</div>
                            </t-col>
                            <t-col style="display: inline;" :span="8">
                                <div v-for="(item, index) in 1">
                                    <t-popup placement="left-bottom">
                                        <template #content>
                                            <t-button shape="square" variant="text">
                                                <t-icon name="add" />
                                            </t-button>
                                            <t-button shape="square" variant="text">
                                                <t-icon name="delete" />
                                            </t-button>
                                        </template>
                                        <t-row :id="index === 0 ? '' : 'table'">
                                            <t-col id="table" :span="6">
                                            </t-col>
                                            <t-col id="table" :span="6">
                                            </t-col>
                                        </t-row>
                                    </t-popup>
                                </div>
                            </t-col>
                        </t-row>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">学生社团活动情况</div>
                    </t-col>
                    <t-col style="display: inline;" :span="9">
                        <t-row style="border-left: 2px solid #000;">
                            <t-col :span="4">
                                <div class="text">活动名称</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <div class="text">举办时间</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <div class="text">活动成效</div>
                            </t-col>
                        </t-row>
                        <div v-for="(item, index) in 1">
                            <t-popup placement="left-bottom">
                                <template #content>
                                    <t-button shape="square" variant="text">
                                        <t-icon name="add" />
                                    </t-button>
                                    <t-button shape="square" variant="text">
                                        <t-icon name="delete" />
                                    </t-button>
                                </template>
                                <t-row style="border-left: 2px solid #000;" id="table">
                                    <t-col :span="4">
                                    </t-col>
                                    <t-col id="table" :span="4">
                                    </t-col>
                                    <t-col id="table" :span="4">
                                    </t-col>
                                </t-row>
                            </t-popup>
                        </div>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">社团育人成效案例</div>
                    </t-col>
                    <t-col :span="9" id="table">另附页提交，详见附录要求</t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">社团代表性照片</div>
                    </t-col>
                    <t-col :span="9" id="table">请提供一张高清照片，照片以学生社团名称命名</t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">社团工作简介</div>
                    </t-col>
                    <t-col :span="9" id="table">
                        <t-textarea placeholder="（2022年5月至今，不超200字）" :autosize="{ minRows: 5, maxRows: 5 }" />
                    </t-col>
                </t-row>
            </t-row>
        </div>
        <div class="attachContainer">
            <ul style="list-style: none;">
                <h2>附录：学生社团育人成效案例撰写指引</h2>
                <li>
                    <h3>一、标题</h3>
                    <p>学生社团名称：15字以内标题（方正小标宋，小二，居中）
                        （例：国旗班：守护国旗，厚植家国情怀）</p>
                </li>
                <li>
                    <h3>二、正文</h3>
                    <ol>
                        <li>摘要：高度凝练学生社团的举措和育人成效，字数150字以内。</li>
                        <li>简要阐述特色做法和育人效果，字数400字以内。</li>
                        <li>全篇行间距1.5倍，正文内容每段统一空两格，宋体，小四。</li>
                    </ol>
                </li>
                <li>
                    <h3> 三、撰写要求</h3>
                    <ol>
                        <li>
                            紧密围绕立德树人和学校人才培养目标，不同类别学生社团育人成效可着重从以下方面考查：
                            <ul>
                                <li>思想政治类社团注重学习传播马克思主义和马克思主义中国化理论成果，特别是习近平新时代中国特色社会主义思想活动开展情况和成效</li>
                                <li>学术科技类社团注重提高学生学习能力、促进学生学业水平的活动开展情况和成效</li>
                                <li>体育竞技类社团注重增强学生体质健康水平的活动开展情况和成效</li>
                                <li>志愿公益类社团注重增强学生劳动能力、培养学生家国情怀等方面活动开展情况和成效</li>
                                <li>文化艺术类社团注重繁荣校园文化、传播中华优秀传统文化等方面的活动开展情况和成效</li>
                            </ul>
                        </li>
                        <li>学生社团党建以及临时团支部工作方面的描述。</li>
                        <li>每个社团聚焦一个项目、活动或主题，也可以全面反映社团特色。内容要高度凝练，结构层次清晰，或用数据（数据可用表格、图表展示），或用事实，确保论述清晰明确。</li>
                    </ol>
                </li>
            </ul>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import eventEmitter from '@/utils/eventEmitter';
import { reactive } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const clubId = route.params.cid;

// 获取社团信息
eventEmitter.emit(APIEventEnum.request, APIEnum.getClubEvaluateInfo, { clubId })

eventEmitter.on(APIEventEnum.getClubEvaluateInfoSuccess, (data) => {
    console.log(data)
})

const clubEvaluation = reactive({

})
</script>