# Game-Reporter
A bug report API that integrates with Discord to send player in-game bug reports to a specified Discord channel. 

## Setup

Game Reporter is meant to be easy for anyone to set up. If you run into any issues in the setup process, post a question in [Discussion](https://github.com/Ceichert31/Game-Reporter/discussions).

1. Fork repo
2. Setup Render and connect repo
3. Set Discord webhook URL in environment file

### Hosting

Game-Reporter requires a hosting service to run the API. I recommend [Render](https://render.com/) as it has a free tier. The free tier does have a slow startup time, so bug reports may take about a minute to come in. 

1. Fork this repository so you have your own version of it.
<img width="928" height="94" alt="image" src="https://github.com/user-attachments/assets/a2ede2e4-1e02-4c15-8dd8-7c8995cc6d3e" />

2. Open [Render](https://render.com/) and click **New**, then select **Web Service**
<img width="1029" height="395" alt="image" src="https://github.com/user-attachments/assets/a1c399eb-62ac-4e73-bf3a-867bd868a2e6" />
<img width="1034" height="399" alt="image" src="https://github.com/user-attachments/assets/09529a96-6eb5-4562-88e9-c7449ede5cf9" />

3. Choose **Public Repository**, paste in your forked repository, and then click **Connect**
<img width="811" height="165" alt="image" src="https://github.com/user-attachments/assets/0fc48f16-f7d5-4162-a36f-2531ad8c17e6" />

4. Then set the **Language** as **Docker**, and the **Root Directory** as **bugreport/**
<img width="1228" height="382" alt="image" src="https://github.com/user-attachments/assets/b20c3b0e-0c4f-4fcf-98e0-0819450e00ef" />

5. Select the free plan
<img width="1208" height="311" alt="image" src="https://github.com/user-attachments/assets/d9b27471-37cd-4550-97bf-435eb65a8094" />

6. For your environment variables, add two entries. One is the Discord Webhook URL. The other is the fallback port. Make sure to copy the key names in the image.
<img width="926" height="126" alt="image" src="https://github.com/user-attachments/assets/a6585949-2e06-47de-9c6c-ae9fef85a3cb" />

7. Now you can click **Deploy** at the bottom of the screen, and then navigate to your dashboard.

8. Your link to your API will be here on the dashboard. This is what allows you to send POST Requests.
<img width="438" height="158" alt="image" src="https://github.com/user-attachments/assets/388931f4-b031-48b2-82fd-367dfc411ffb" />



## Implementation
### C# Implementation
```C#
using System;
using System.Net.Http;
using System.Net.Http.Json;
using System.Threading.Tasks;


public class BugReport
{
	public string Title { get; set; }
	public string Message { get; set; }
}

/// Handles sending HTTP POST requests to the reporter API
public class Reporter
{
	private string reportUrl = "https://<your-render-domain>.onrender.com"
	private readonly HttpClient client = new HttpClient();

	private async Task SubmitBugReport(report BugReport)
	{
		try
		{
			var response = await client.PostAsJsonAsync(reportUrl, report);

			response.EnsureSuccessStatusCode();
		}
		catch (HttpRequestException e)
		{
			Console.WriteLine($"Failed to submit bug report: {e.Message}");
		}
	}
}
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
