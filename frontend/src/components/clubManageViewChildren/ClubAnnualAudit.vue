<style scoped>
.AnnualAuditContainer {
    width: 100%;
    height: 690px;
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

.txt {
    font-size: 14px;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 10px;
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

.downloadContainer {
    width: 80%;
    margin: 0 auto 50px auto;
}
</style>

<template>
    <div class="AnnualAuditContainer">
        <div class="AnnualAuditTitle">学生社团年审申报表</div>
        <div class="tableContainer">
            <t-row id="border">
                <t-row>
                    <t-col :span="3">
                        <div class="txt">学生社团</div>
                    </t-col>
                    <t-col id="table" :span="3"></t-col>
                    <t-col id="table" :span="3">
                        <div class="txt">学生社团类别</div>
                    </t-col>
                    <t-col id="table" :span="3"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">业务指导单位</div>
                    </t-col>
                    <t-col id="table" :span="3"></t-col>
                    <t-col id="table" :span="3">
                        <div class="txt">学生社团总人数</div>
                    </t-col>
                    <t-col id="table" :span="3"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">行政指导老师</div>
                    </t-col>
                    <t-col id="table" :span="3"></t-col>
                    <t-col id="table" :span="3">
                        <div class="txt">业务指导老师</div>
                    </t-col>
                    <t-col id="table" :span="3"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">学生社团分布校园（区）</div>
                    </t-col>
                    <t-col id="table" :span="9"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">学生负责人姓名</div>
                    </t-col>
                    <t-col id="table" :span="3"></t-col>
                    <t-col id="table" :span="3">
                        <div class="txt">学生负责人政治面貌</div>
                    </t-col>
                    <t-col id="table" :span="3"></t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">宣传管理情况</div>
                    </t-col>
                    <t-col style="display: inline" :span="9">
                        <t-row>
                            <t-col id="table" :span="3">
                                <div class="txt">微信公众号</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="3">
                                <div class="txt">网站</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="3">
                                <div class="txt">微博</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="3">
                                <div class="txt">哔哩哔哩</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="3">
                                <div class="txt">抖音</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="3">
                                <div class="txt">微信视频号</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="3">
                                <div class="txt">快手</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="3">
                                <div class="txt">刊物</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="3">
                                <div class="txt">其他</div>
                            </t-col>
                            <t-col id="table" :span="9"></t-col>
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
                                <div class="txt">是否接受校外赞助（如有需填报附件3）</div>
                            </t-col>
                            <t-col id="table" :span="4"></t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="8">
                                <div class="txt">是否向社团成员进行财务公开</div>
                            </t-col>
                            <t-col id="table" :span="4"></t-col>
                        </t-row>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">学生社团章程</div>
                    </t-col>
                    <t-col id="table" :span="9">请学生社团另行提供</t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">会议及活动清单</div>
                    </t-col>
                    <t-col id="table" :span="9">包括全员大会、骨干会议、日常活动等，详见附件2</t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">院系党组织/职能部门意见</div>
                    </t-col>
                    <t-col id="table" :span="9"></t-col>
                </t-row>
            </t-row>
        </div>
        <div style="margin:50px auto 50px auto;width: 100%;display: flex;justify-content: center; align-items: center;">
            <t-button @click="">提交</t-button>
        </div>
        <div class="downloadContainer">
            <a style="display: block;" href="/download/附件2：2023年学生社团年审活动清单.xlsx" download>附件2：2023年学生社团年审活动清单</a>
            <a style="display: block;" href="/download/附件3：2023年学生社团年审校外赞助清单.xlsx" download>附件3：2023年学生社团年审校外赞助清单</a>
        </div>
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';

const route = useRoute()
const clubId = route.params.cid
console.log(clubId);

const downloadFile = (url, fileName) => {
    const link = document.createElement('a')
    link.href = url
    link.download = fileName // 指定下载后的文件名
    link.click()
}
</script>