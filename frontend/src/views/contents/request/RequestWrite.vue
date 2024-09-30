<template>
  <h1 class="title">요청서 작성</h1>
  <section class="container">
    <form>
      <b-form-group label="희망 이벤트" label-sr-only v-slot="{ ariaDescribedby }">
    <b-form-radio-group
      id="radio-slots"
      v-model="selected"
      :options="options"
      :aria-describedby="ariaDescribedby"
      name="category-options-slots"
    ></b-form-radio-group>
  </b-form-group>

      <b-container fluid>
      <b-row class="my-1 input-row" v-for="item in types" :key="item.id">
        <b-col sm="4">
          <label :for="`${item.id}`">{{ item.label }}</label>
        </b-col>
        <b-col sm="7">
          <b-form-input :id="`${item,id}`" :type="item.type"></b-form-input>
        </b-col>
      </b-row>
    </b-container>
    </form>
    <div id="button">
    <button @click="submitEvent('요청서 제출이 완료되었습니다!')">작성 완료</button>
    </div>
</section>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const selected = ref('');
const options = [
  { text: "생일 이벤트", value: "생일 이벤트" },
  { text: "단체 파티", value: "단체 파티" },
  { text: "장소 대관", value: "장소 대관" },
  { text: "장례 서비스", value: "장례 서비스" }
];

const types = [
  { id: 'event', label: '요청 이벤트', type: 'text' },
  { id: 'cost', label: '희망 비용', type: 'number' },
  { id: 'location', label: '희망 장소', type: 'text' },
  { id: 'date', label: '희망 날짜', type: 'date' }
];

const router = useRouter();

// 작성 완료 후 요청서 목록으로 넘어감.
function submitEvent() {
  return new Promise((resolve) => {
    alert("요청서 제출이 완료되었습니다!");
    console.log("요청서 제출이 완료되었습니다!");
    resolve();
  }).then(() => {
    router.push('/requestlist');
  })};
</script>

<style scoped>
.container{
  overflow: auto;
  height: 100vh;
}
h1 {
  color: #49D5FF;
  font-family: Jua;
  margin: 60px;
}
form {
  background-color: rgba(163, 225, 253, 0.384);
  padding: 20px;
  border-radius: 20px;
  max-width: 700px;
  margin: auto;
  text-align: center;
  display: flex;
  flex-direction: column;
}

form > * {
  margin-bottom: 20px;
}

.b-form-radio-group {
  display: flex;
  justify-content: space-between;
  margin-bottom: 2rem;
}

.b-container {
  margin: 20px 0;
}

.input-row {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.input-row:last-child {
  margin-bottom: 0;
}
button {
  display: block;
  margin: 20px auto;
  padding: 10px 20px;
  border: 3px solid #a5e0fc;
  background-color: white;
  border-radius: 5px;
  cursor: pointer;
}
</style>
