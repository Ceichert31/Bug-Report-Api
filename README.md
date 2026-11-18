# Game-Reporter
A bug report API that integrates with Discord to send player in-game bug reports to a specified Discord channel. 

## Setup

Game-Reporter requires a hosting service to run the API. I recommend [Render](https://render.com/) as it has a free tier. The free tier does have a slow startup time, so bug reports may take about a minute to come in. 

## Implemetation

### C# Implementation
```C#
private string reportUrl = "https://<your-render-domain>.onrender.com"

private void SubmitBugReport(string title, string message) {}
```

### GDScript Implementation
```GDScript
var _report_url := "https://<your-render-domain>.onrender.com"

func submit_bug_report(title: String, message: String) -> void:
  var report = {
    "title": title,
    "message": message
  }

  var json_string = JSON.stringify(report)
  var headers = ["Content-Type: application/json"]
  var response = http_request.request(
		url, 
		headers, 
		HTTPClient.METHOD_POST, 
		json_string
	)

  if response != OK:
		push_error("An error occurred while trying to submit the bug report. Please check your internet connection and try again.")
```

# ToDo
- [ ] Setup screenshot support
- [ ] Setup log file/game state support
- [ ] Create guide for setting up Render + integration into game
