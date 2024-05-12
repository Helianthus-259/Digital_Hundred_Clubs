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
      component: () => import('@/views/AdminFirstPage.vue'),
      meta: { requireAuth: false, },
      children: [
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
      children: [
        {
          path: '',
          name: 'clubEdit',
          component: () => import('../components/clubManageViewChildren/ClubEdit.vue'),
          meta: { requireAuth: true, },
        },
        {
          path: 'publish',
          name: 'clubPublish',
          component: () => import('../components/clubManageViewChildren/ClubPublish.vue'),
          meta: { requireAuth: true, },
        },
        {
          path: 'check',
          name: 'clubCheck',
          component: () => import('../components/clubManageViewChildren/ClubCheck.vue'),
          meta: { requireAuth: true, },
        },
        {
          path: 'apply',
          name: 'clubApply',
          component: () => import('../components/clubManageViewChildren/ClubApply.vue'),
          meta: { requireAuth: true, },
        },
      ]
    },
  ]
})

eventEmitter.on(RouterEventEnum.push, (path) => {
  router.push(path)
})

eventEmitter.on(RouterEventEnum.go, (n) => {
  router.go(n)
})

export default router
