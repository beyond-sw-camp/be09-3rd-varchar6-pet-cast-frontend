<template>
    <section class="container">
        <InviteTitle/>

        <article class = "input-info">
            <InviteInput/>
            <InviteBtn/>
        </article>
    </section>
</template>

<script setup>
    import InviteTitle from '@/components/body/gather/invitation/InviteTitle.vue';
    import InviteInput from '@/components/body/gather/invitation/InviteInput.vue';
    import InviteBtn from '@/components/body/gather/invitation/InviteBtn.vue';

</script>

<style scoped>
    .container {
        overflow: auto;
        height: 70vh;
    }
</style>