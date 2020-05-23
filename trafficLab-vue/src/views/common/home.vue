<template>
  <div class="mod-home">
    <jingjiahome></jingjiahome>
  </div>

</template>

<script>
  import jingjiahome from '../modules/jingjia/jingjia-home'
  export default {
    data () {
      return {
      }
    },
    components: {
      jingjiahome
    },
    created () {
      this.getUserInfo()
    },
    methods: {
      // 获取当前管理员信息
      getUserInfo () {
        this.$http({
          url: this.$http.adornUrl('/sys/user/info'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.userId = data.user.userId
            this.userName = data.user.username
          }
        })
      }
    }
  }
</script>

<style>

  .mod-home {
    line-height: 1.5;
  }
</style>

