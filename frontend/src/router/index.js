import { createRouter, createWebHistory } from 'vue-router'
import FirstPageView from '../views/FirstPageView.vue'
import eventEmitter from '@/utils/eventEmitter'
import { RouterEventEnum } from '@/Enum'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 首页
    {
      path: '/',
      name: 'firstPage',
      component: FirstPageView,
      redirect: '/home', // 添加重定向路由，将根路径重定向到 /home
      meta: { requireAuth: false, },
      children: [
        {
          path: 'home',
          name: 'home',
          component: () => import('../components/firstPageViewChildren/HomeView.vue'),
          redirect: '/home/clubs',
          meta: { requireAuth: false, },
          children: [ // 首页中的子界面
            {
              path: 'clubs',
              name: 'clubs',
              component: () => import('../components/homeViewChildren/ClubsView.vue'),
              meta: { requireAuth: false, },
            },

            {
              path: 'news',
              name: 'news',
              component: () => import('../components/homeViewChildren/NewsView.vue'),
              meta: { requireAuth: false, },
            },
          ]

        },

        { // 个人界面
          path: 'personal/:uid',
          name: 'personal',
          component: () => import('../components/firstPageViewChildren/PersonalView.vue'),
          meta: { requireAuth: true, },
          children: [
            {
              path: '',
              name: 'personalInfo',
              component: () => import('@/components/personalViewChildren/PersonalInfo.vue'),
              meta: { requireAuth: true, },
            },
            {
              path: 'participatedClubs',
              name: 'participatedClubs',
              component: () => import('@/components/personalViewChildren/ParticipatedClubs.vue'),
              meta: { requireAuth: true, },
            },
            {
              path: 'participatedActivities',
              name: 'participatedActivities',
              component: () => import('@/components/personalViewChildren/ParticipatedActivities.vue'),
              meta: { requireAuth: true, },
            },
          ]
        },
      ]
    },
    // 登陆界面
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
      meta: { requireAuth: false, },
    },
    // 管理员登陆界面
    {
      path: '/adminLogin',
      name: 'adminLogin',
      component: () => import('../views/AdminLoginView.vue'),
      meta: { requireAuth: false, },
    },
    // 管理员首页界面
    {
      path: '/adminFirstPage',
      name: 'adminFirstPage',
      component: () => import('../views/AdminFirstPage.vue'),
      redirect: '/adminFirstPage/admin',
      meta: { requireAuth: false, },

      children: [
        {
          path: 'admin',
          name: 'admin',
          component: () => import('../components/adminViewChildren/adminHomeView.vue'),
          redirect: '/adminFirstPage/admin/adminClub',
          meta: { requireAuth: false, },
          children: [ // 管理员首页中的子界面
            {//管理员首页也可以查看社团信息
              path: 'adminClub',//管理员是否也需要查看各个社团信息？如果需要，是否应当修改社团详细信息中部分构件的显示关系（如管理员不能提交入社申请
              name: 'adminClub',
              component: () => import('../components/adminHomeViewChildren/ClubsView.vue'),
              meta: { requireAuth: false, },
            },
            {
              path: 'adminNews',
              name: 'adminNews',
              component: () => import('../components/adminHomeViewChildren/NewsView.vue'),
              meta: { requireAuth: false, },
            },
          ]
        },
        {
          path: 'adminPersonal',
          name: 'adminPersonal',
          component: () => import('../components/adminViewChildren/adminPersonalView.vue'),
          redirect: '/adminFirstPage/adminPersonal/adminPersonalInfo',
          meta: { requireAuth: true, },
          children: [
            {
              path: 'adminPersonalInfo',//管理员自己的信息，如管理员分类（社团管理/校级管理）、社团/成员信息、社团活动/评优申请等。
              name: 'adminPersonalInfo',
              component: () => import('@/components/adminPersonalViewChildren/adminPersonalInfo.vue'),
              meta: { requireAuth: true, },
            },
            {
              path: 'managedClub',//管理员所管理的社团信息（如果是社团管理员）
              name: 'managedClub',
              component: () => import('@/components/adminPersonalViewChildren/managedClub.vue'),
              meta: { requireAuth: true, },
            },
            {
              path: 'clubActivitiesHistory',//本社团的活动记录
              name: 'clubActivitiesHistory',
              component: () => import('@/components/adminPersonalViewChildren/clubActivitiesHistory.vue'),
              meta: { requireAuth: true, },
            },
            {
              path: 'examHistory',
              name: 'examHistory',
              component: () => import('@/components/adminPersonalViewChildren/examHistory.vue'),
              meta: { requireAuth: true, },
            },
          ]
        },
        { // 管理员审批页面
          path: 'examine',
          name: 'examine',
          component: () => import('@/components/adminViewChildren/AdminExamine.vue'),
          meta: { requireAuth: true, },
          children: [
            {
              path: '',
              name: 'clubApproval',
              component: () => import('@/components/AdminExamineChildren/ClubApproval.vue'),
              meta: { requireAuth: true, },
            },
            {
              path: 'activityApproval',
              name: 'activityApproval',
              component: () => import('@/components/AdminExamineChildren/ActivityApproval.vue'),
              meta: { requireAuth: true, },
            },
          ]
        },

      ]

    },
    // 社团界面
    {
      path: '/club/:cid',
      name: 'club',
      component: () => import('../views/ClubView.vue'),
      meta: { requireAuth: true, },
      children: [
        {
          path: '',
          name: 'clubIntroduction',
          component: () => import('../components/ClubViewChildren/ClubIntroduction.vue'),
          meta: { requireAuth: true, }
        },
        {
          path: 'clubActivities',
          name: 'clubActivities',
          component: () => import('../components/ClubViewChildren/ClubActivities.vue'),
          meta: { requireAuth: true, }
        },
        {
          path: 'clubMembers',
          name: 'clubMembers',
          component: () => import('../components/ClubViewChildren/ClubMembers.vue'),
          meta: { requireAuth: true, }
        }
      ]
    },
    // 活动界面
    {
      path: '/activity/:aid',
      name: 'activity',
      component: () => import('../views/ActivityView.vue'),
    },
    // 社团管理界面
    {
      path: '/clubManage/:cid',
      name: 'clubManage',
      component: () => import('../views/ClubManageView.vue'),
      meta: { requireAuth: true, },
    },
  ]
})

eventEmitter.on(RouterEventEnum.push, (path, newWindow = false) => {
  if (newWindow) {
    const resolveRoute = router.resolve(path)
    window.open(resolveRoute.href, '_blank')
  } else {
    router.push(path)
  }
})

eventEmitter.on(RouterEventEnum.go, (n) => {
  router.go(n)
})

export default router
