<template>
    <b-form-group label="URL" label-for="urlInput">
        <h6>{{ url }}</h6>
    </b-form-group>
</template>

<script setup>
    import { ref, onMounted } from 'vue';

    const url = ref('');

    onMounted(() => {
        url.value = window.location.href;
    });
</script>

<style scoped>
    .urlinput{
        height: 50px;
        width: 100%;
        resize: none;
    }
</style>