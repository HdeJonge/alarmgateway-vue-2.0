<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Gateway details</h4>
      <div>
        <label>id:</label>
        {{this.alarmgateway.id}}
      </div>
      <div>
        <label>mac:</label>
        {{this.alarmgateway.mac}}
      </div>
      <div>
        <label>setting:</label>
        {{this.alarmgateway.settingsProfile}}
      </div>
      <div>
        <label>location:</label>
        <div
          v-if="this.alarmgateway.location!=null"
        >{{this.alarmgateway.location.locationDescription}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["alarmgateway"]

  /* eslint-enable no-console */
};
</script>