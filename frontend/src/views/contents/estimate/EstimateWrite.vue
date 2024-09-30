<template>
  <h1>견적서 작성</h1>
  <div class="container mt-3">
    <form @submit.prevent="submitForm">
      <div class="mb-3">
        <label for="event" class="form-label">요청 이벤트</label>
        <input
          type="text"
          id="event"
          class="form-control"
          v-model="form.event"
          readonly
        />
      </div>

      <div class="mb-3">
        <label for="date" class="form-label">요청 날짜</label>
        <input type="date" id="date" class="form-control" v-model="form.date" />
      </div>

      <div class="mb-3">
        <label for="cost" class="form-label">예상 비용</label>
        <input
          type="number"
          id="cost"
          class="form-control"
          v-model="form.cost"
          placeholder="예상 비용을 입력하세요"
        />
      </div>
    </form>
    <button @click="submitEvent">작성 완료</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import proposalData from "@/js/data-temp.json";

const router = useRouter();
// const proposalId = router.params.id;
const proposalId = router.currentRoute.value.params.id;

const form = ref({
  event: "",
  date: "",
  cost: "",
});

// onMounted에서 제목을 자동으로 입력(json에서 가져와 이벤트와 날짜 자동 입력)
onMounted(() => {
  const proposal = proposalData.proposal.find(
    (p) => p.id === Number(proposalId)
  );
  if (proposal) {
    form.value.event = proposal.content;
    const date = new Date(proposal.hope_time);
    form.value.date = date.toISOString().split("T")[0];
  }
});

function submitEvent() {
  return new Promise((resolve) => {
    alert("견적서 작성이 완료되었습니다!");
    console.log("견적서 제출이 완료되었습니다!");
    resolve();
  }).then(() => {
    router.push("/proposalaccept");
  });
}
</script>

<style scoped>
.container {
  max-width: 600px;
}
</style>

<style scoped>
.container {
  overflow: auto;
  height: 100vh;
}
h1 {
  color: #49d5ff;
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
  margin: 20px auto 0;
  padding: 10px 20px;
  border: 3px solid #a5e0fc;
  background-color: white;
  border-radius: 5px;
  cursor: pointer;
}
</style>
