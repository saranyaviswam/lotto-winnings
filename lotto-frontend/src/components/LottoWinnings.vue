<template>
  <div class="lotto-winning">
    <h1> Check your lotto numbers for winning history!</h1>
    <p>
      Enter Six numbers (between 1 and 49) below and we will check them against every 6/49 draw since 1981, and calculate your net winnings (or losings).
      </p>
    
    <p v-if="errors.length">
      <b>Please correct the following error(s):</b>
      <ul class="error-block">
        <li v-for="error in errors" :key="error">{{ error }}</li>
      </ul>
    </p>
    <p>
    <input v-model="number1" id="number1" style="margin: 10px" size="2" min="1" max="49" type="number">    
    
    <input v-model="number2" id="number2" style="margin: 10px" size="2" min="1" max="49" type="number">
    
    <input v-model="number3" id="number3" style="margin: 10px" size="2" min="1" max="49" type="number">
    
    <input v-model="number4" id="number4" style="margin: 10px" size="2" min="1" max="49" type="number">
    
    <input v-model="number5" id="number5" style="margin: 10px" size="2" min="1" max="49" type="number">
    
    <input v-model="number6" id="number6" style="margin: 10px" size="2" min="1" max="49" type="number">
    <br/>
    <br/>

    <button class="button" @click="getWinnings">Check the Numbers</button>
    <vue-instant-loading-spinner ref="Spinner"></vue-instant-loading-spinner>

    </p>
    <!--pre>{{listData}}</pre-->

    <table v-if="hasSearched"> 
      <thead>
        
        <tr>
          <th>Draw Date</th>
          <th>Win Amount</th>
        </tr>
      </thead>
      <tbody>
          <tr v-for="winning in listData.winningDetails" :key="winning.id">
              <td>{{winning.winningDate}}</td>
              <td>${{winning.amount.toLocaleString()}}</td>
          </tr>
           
      </tbody>
      <tfoot>
        <tr>
              <td>Total Won</td>
              <td>${{listData.totalWon.toLocaleString()}}</td>
          </tr>
          <tr>
              <td>You Spent</td>    
              <td>${{listData.totalSpent.toLocaleString()}}</td>
            </tr>
          <tr>
              <td>Net Won/Loss</td>     
              <td :style="listData.netWonOrLoss < 0 ? 'color:red' : 'color:green'">${{listData.netWonOrLoss.toLocaleString()}}</td>
            </tr> 
      </tfoot>
    </table>

  </div>
</template>

<script>
import VueInstantLoadingSpinner from 'vue-instant-loading-spinner/src/components/VueInstantLoadingSpinner.vue'

export default {
  name: 'LottoWinnings',
  components: {
    VueInstantLoadingSpinner
  },
  data() {

    return{
      number1: 1,
      number2: 1,
      number3: 1,
      number4: 1,
      number5: 1,
      number6: 1,
      listData: [], // placeholder,
      hasSearched: false,
      errors: []
    }
  },
  methods: {
    getWinnings() {
      this.hasSearched=false
      this.listData = []
      this.validate()
      if (this.errors.length > 0) {
        return
      }
      this.$refs.Spinner.show();
      console.log(process.env.VUE_APP_API_ENDPOINT)
      this.axios.get(`${process.env.VUE_APP_API_ENDPOINT}?n1=${this.number1}&n2=${this.number2}&n3=${this.number3}&n4=${this.number4}&n5=${this.number5}&n6=${this.number6}`).then((response) => {
        console.log(response.data)
        this.listData = response.data;
        this.hasSearched=true
        this.$refs.Spinner.hide();
      })

    },
    validate() {
      this.errors = []
      let valueArr = [this.number1, this.number2, this.number3, this.number4, this.number5, this.number6]

      for (let val of valueArr) {
        if (val < 1 || val > 49) {
          console.log(val)
          this.errors.push('Please enter valid numbers between 1 and 49')
          // break so that same error is displayed only once
          break
        }
      }

      // Check for duplicates
      if ((new Set(valueArr)).size !== 6) {
        this.errors.push('Please enter unique numbers between 1 and 49')
      }
    }

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.button {
  margin: 30px;
  background-color: #39495c;
  border-radius: 5px;
  font-size: 18px;
  width: 250px;
  height: 60px;
  color: white;
  padding: 20px;
  box-shadow: inset 0 -0.6em 1em -0.35em rgba(0, 0, 0, 0.17),
    inset 0 0.6em 2em -0.3em rgba(255, 255, 255, 0.15),
    inset 0 0 0em 0.05em rgba(255, 255, 255, 0.12);
  text-align: center;
  cursor: pointer;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #00006E;
}
.nav-bar {
  background: linear-gradient(-90deg, #84cf6a, #16c0b0);
  height: 60px;
  margin-bottom: 25px;
  -webkit-box-shadow: 0px 2px 15px -12px rgba(0, 0, 0, 0.57);
  -moz-box-shadow: 0px 2px 15px -12px rgba(0, 0, 0, 0.57);
  box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.57);
}
.error-block {
  color: red;
}
table {
  border: 2px solid #42b983;
  border-radius: 3px;
  background-color: #fff;
  margin: 0 auto; /* or margin: 0 auto 0 auto */
}

th {
  background-color: #42b983;
  color: rgba(255, 255, 255, 0.66);
  cursor: pointer;
  user-select: none;
}

td {
  background-color: #f9f9f9;
}

th,
td {
  min-width: 120px;
  padding: 10px 20px;
}

th.active {
  color: #fff;
}

th.active .arrow {
  opacity: 1;
}

.arrow {
  display: inline-block;
  vertical-align: middle;
  width: 0;
  height: 0;
  margin-left: 5px;
  opacity: 0.66;
}

.arrow.asc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-bottom: 4px solid #fff;
}

.arrow.dsc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-top: 4px solid #fff;
}

tfoot {
  font-weight: bold;
}
</style>
