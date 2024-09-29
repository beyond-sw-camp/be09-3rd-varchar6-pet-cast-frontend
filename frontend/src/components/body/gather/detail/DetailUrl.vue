<template>
    <b-form-group label="URL" label-for="urlInput">
        <h6>{{ url }}</h6>
    </b-form-group>
</template>

<script setup>
    defineProps({
        url:
        {
            type: String,
            required: true
        }
    });
</script>

<style scoped>
    .urlinput{
        height: 50px;
        width: 100%;
        resize: none;
    }
</style>