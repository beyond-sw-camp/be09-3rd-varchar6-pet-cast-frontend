<template>
    <section class="section">
        <div class="container">
            <ListTitle/>
            <ListBtn @update-select="handleSelectUpdate"/>
            <ListChart :selectValue="selectValue"/>
        </div>
    </section>

</template>

<script setup>
    import { ref } from 'vue';

    import ListTitle from '@/components/body/gather/list/ListTitle.vue';
    import ListChart from '@/components/body/gather/list/ListChart.vue';
    import ListBtn from '@/components/body/gather/list/ListBtn.vue';

    const selectValue = ref(true);

    const handleSelectUpdate = (newValue) => {
        selectValue.value = newValue;
    }
</script>

<style scoped>
    .section{
        overflow: auto;
    }
    .container{
        display: flex;
        flex-direction: column;
    }   
</style>