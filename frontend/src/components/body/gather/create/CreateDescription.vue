<template>
    <b-card title="모임 정보" class="card">
        <CreateMember/> 
    
        <CreateContents/>

        <CreateUrl/>
                
        <CreatedAt/>
<!-- <b-button type="submit" variant="primary">수정</b-button> -->
    </b-card>
</template>

<script setup>
    import CreateMember from './CreateMember.vue';
    import CreateContents from './CreateContents.vue'; 
    import CreateUrl from './CreateUrl.vue';
    import CreatedAt from './CreatedAt.vue';
</script>

<style scoped>
    .card {
        background-color: #DDF5FF;
    }
</style>