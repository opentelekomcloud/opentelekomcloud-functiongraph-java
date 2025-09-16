# Sample for Timer Event

TimerTriggerFG is a sample function that demonstrates how to handle timer events.
It processes the incoming event and logs the trigger name, type, user event, and time.

## Configuration

**Basic Settings** -> **Handler name:** com.opentelekomcloud.samples.TimerTriggerFG.handleRequest

**Trigger Configuration**:
If you create Trigger using console, add following json to

``Additional Information``:
```json
{ 
  "message": "timer triggered event",
  "topic":"test"
}
```
