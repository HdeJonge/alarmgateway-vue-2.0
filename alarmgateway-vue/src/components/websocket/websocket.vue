<template>
  <div id="content">
    <button id="connectButton" onclick="connect();" class="btn btn-primary" type="button">connect</button>
    <button v-on:click="connect" class="btn btn-success">CONN</button>
    <div id="messages"></div>
    <p>FOO: {{foo}}</p>
    <p>LOGS{{logs}}</p>
    <p>{{baz}}</p>
    <p>{{messages}}</p>
  </div>
</template>
<script>
import { eventBus } from "../../main";
import SockJS from "sockjs-client";
export default {
  data() {
    return {
      logs: ["foo", "bar"],
      messages: [],
      foo: "",
      bar: "",
      baz: "",
      stomp: null
    };
  },
  methods: {
    connect() {
      console.log("connecting");
      this.soc = new SockJS("http://localhost:8080/message");
      // this.soc =  new WebSocket("ws://localhost:8080/message");
      this.logs.push("baz");
      this.soc.onopen = function() {
        console.log("open");
      };
      this.soc.onmessage = function(event) {
        var data = JSON.parse(event.data);
        var messages = document.getElementById("messages");
        var message = document.createElement("li");
        var content = document.createTextNode(event.data);
        message.appendChild(content);
        messages.appendChild(message);
        eventBus.$emit("newMessage", data.message);
      };
    }
  },
  created() {
    this.connect();
    eventBus.$on("newMessage", data => {
      console.log(data);
      this.logs.push(data);
    });
  },
  mounted() {}
};
</script>

