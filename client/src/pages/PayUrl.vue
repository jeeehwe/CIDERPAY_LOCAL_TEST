<template>
    <div id="main">
        <div v-if="isUrlCorrect" class="main">
            <img src="@/assets/logo.png" class="space">
            <div>
                상품명 : {{ paymentRequest.goodName }}<br/>
                판매자 : {{ paymentRequest.sellerName }}<br/>
                연락처 : {{ paymentRequest.mobile }}<br/>
                결제금액 : {{ paymentRequest.price }}<br/>
                결제 상태 : <span style="color: red">{{ paymentRequest.paymentState }}</span>
            </div><br/>
            <div class="space">카드결제</div><br/>
            <button v-if="isRequest" class="btn btn-success" @click="makePayment">결제하기</button>
            <button v-if="!isRequest" class="btn btn-danger" @click="paymentRequestCancel">결제 요청 취소</button>{{' '}}
            <button class="btn btn-outline-dark" @click="goBack">되돌아가기</button>
        </div>

        <div v-else>잘못된 주소입니다.</div>
    </div>
</template>

<script>
import axios from 'axios'
import api from "@/api/paymentApi";
import view from "@/api/view/paymentView";

export default {
    name: "PayUrl",
    data() {
        return {
            paymentRequest: {},
            isUrlCorrect: false,
            isRequest: false,
        }
    },
    created() {
        axios
            .get(`http://localhost:8080/p/${this.$route.params.payUrl}`)
            .then(({data}) => {
                if (data !== "") {
                    this.isUrlCorrect = true;
                    this.paymentRequest = data;
                    this.isRequest = (data.paymentState === 'REQUEST');
                } else
                    this.isUrlCorrect = false;
            })
            .catch(() => this.isUrlCorrect = false)
    },
    methods: {
        makePayment() {
            view.makePayment(this.paymentRequest, (res) => {
                this.isRequest = !this.isRequest;
                this.paymentRequest = res.data;
            })
        },
        goBack() {
            this.$router.push('/')
        },
        paymentRequestCancel() {
            api.paymentRequestCancel({
                memberID: this.paymentRequest.memberID,
                payUniqueNo: this.paymentRequest.payUniqueNo
            }, () => {
                alert('결제 요청이 취소되었습니다.');
                setTimeout(() => this.$router.push('/'), 1000)
            })
        }
    }
}
</script>

<style scoped>

#main {
    padding: 3%;
}

.main {
    width: 300px;
    height: 400px;
    text-align: center;
    padding: 1%;
    border: 1px solid dimgray;
    border-radius: 30px;
}

.main > div {
    border: 1px solid lightgray;
    border-radius: 5px;
    width: 100%;
}

.main > :nth-child(2) {
    height: 200px;
    text-align: left;
    padding-top: 40px;
    padding-bottom: 100px;
    padding-left: 10px;
}

.space {
    padding: 3%;
}

</style>
