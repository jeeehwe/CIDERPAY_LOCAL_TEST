<template>
    <div style="padding: 1%">
        <h3 v-if="getRouteParam() === 'payment'">결제 리스트</h3>
        <h3 v-if="getRouteParam() === 'request'">결제 요청 리스트</h3>
        <button class="btn btn-outline-primary" @click="getLists();">새로고침</button>{{' '}}
        <button class="btn btn-primary" @click="goRequestList();">결제 요청 리스트</button>{{' '}}
        <button class="btn btn-primary" @click="goPaymentList();">결제 리스트</button>
        <div v-if="list.length !== 0" class="overflow-auto">
            <div style="padding-top: 10px"></div>
            <b-table
                id="my-table"
                :items="list"
                :per-page="perPage"
                :current-page="currentPage"
                :fields="fields"
                small striped hover
                class="text-center"
            >
                <template #cell(No)="item">{{ item.index+1 }}</template>

                <template #cell(paymentState)="item">
                    <b class="text-info">{{ item.item.paymentState }}</b>
                </template>

                <template #cell(button)="item">
                    <button v-if="getRouteParam() === 'payment' && item.item.paymentState === 'COMPLETE'" class="btn btn-sm btn-success" @click="makeAdjust(item.item)">정산하기</button>{{' '}}
                    <button v-if="getRouteParam() === 'payment' && item.item.paymentState === 'COMPLETE'" class="btn btn-sm btn-danger" @click="cancelPayment(item.item)">결제 취소</button>
                    <button v-if="getRouteParam() === 'payment' && item.item.paymentState === 'ADJUST'" class="btn btn-sm btn-danger" @click="cancelRequestAfterAdjust(item.item)">정산된 결제 취소</button>
                    <button v-if="getRouteParam() === 'request' && item.item.paymentState === 'REQUEST'" class="btn btn-sm btn-primary" @click="makePayment(item.item)">결제하기</button>{{' '}}
                    <button v-if="getRouteParam() === 'request' && item.item.paymentState === 'REQUEST'" class="btn btn-sm btn-outline-primary" @click="cancelPaymentRequest(item.item)">결제 요청 취소</button>{{' '}}
                </template>

            </b-table>
            <b-pagination
                v-model="currentPage"
                :total-rows="list.length"
                :per-page="perPage"
                aria-controls="my-table"
                align="center"
            ></b-pagination>
        </div>
        <div v-else><br/> 내용이 없습니다.</div>
    </div>
</template>

<script>
import axios from "axios";
import PaymentApi from "@/api/paymentApi";
import PaymentView from "@/api/view/paymentView";

export default {
    name: "PaymentList",
    data() {
        return {
            perPage: 10,
            currentPage: 1,
            fields: [
                'No',
                {key: 'goodName', label: '상품명'},
                {key: 'price', label: '상품금액'},
                {key: 'memberID', label: '판매자아이디'},
                {key: 'mobile', label: '구매자 휴대폰'},
                {key: 'createDate', label: '요청일시'},
                {key: 'orderNo', label: '주문 번호'},
                {key: 'feedbackToken', label: '결제 요청 취소 토큰'},
                {key: 'paymentState', label: '결제 상태'},
                {key: 'button', label: ''},
            ],
            list: [],
        }
    },
    created() {
        this.getLists();
    },
    methods: {
        getLists() {
            axios
                .get(`http://localhost:8080/p/list/${this.getRouteParam()}`)
                .then(({data}) => {
                    this.list = data;
                })
        },
        getRouteParam() {
            return this.$route.params.state;
        },
        cancelPayment(item) {
            PaymentApi.cancelPayment({
                memberID: item.memberID,
                orderNo: item.orderNo,
                token: item.token,
                cancelMessage: '취소 요청 사유'
            }, () => {
                alert('결제가 취소되었습니다.');
                this.getLists();
            })
        },
        cancelRequestAfterAdjust(item) {
            PaymentApi.cancelRequestAfterAdjust({
                memberID: item.memberID,
                orderNo: item.orderNo,
                token: item.token,
                cancelMessage: '취소 요청 사유',
                bankInName: '입금자명'
            }, (res) => {
                alert(res.data.errorMessage);
                this.getLists();
            })
        },
        cancelPaymentRequest(item) {
            PaymentApi.cancelPaymentRequest({
                memberID: item.memberID,
                payUniqueNo: item.payUniqueNo
            }, () => {
                alert('결제 요청이 취소되었습니다.');
                this.getLists();
            })
        },
        makeAdjust(item) {
            PaymentView.makeAdjust(item, () => this.getLists());
        },
        makePayment(item) {
            PaymentView.makePayment(item, () => this.getLists());
        },
        goRequestList() {
            if (this.getRouteParam() !== 'request')
                this.$router.push('/list/request');
            this.getLists();
        },
        goPaymentList() {
            if (this.getRouteParam() !== 'payment')
                this.$router.push('/list/payment');
            this.getLists();
        }

    }
}
</script>

<style scoped>

</style>
