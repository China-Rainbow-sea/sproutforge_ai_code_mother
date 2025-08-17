<script setup lang="ts">
import BasicLayout from '@/layouts/BasicLayout.vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'

// 这个APP.vue 是主布局框架,渲染该页面的时候，就加载获取用户登录信息
const loginUserStore =  useLoginUserStore();
loginUserStore.fetchLoginUser();
</script>

<template>
  <BasicLayout />
</template>

<style></style>
