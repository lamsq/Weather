<h2>Project description:</h2>
Сhatbot helps you to arrange the outfit according to the current weather or weather forecast.

It works by getting the current/forecast weather object and returning the appropriate clothes for the chosen city on a specific date.

<h3>Chatbot logic for clothes suggestions:</h3>

<b>Temperature</b> (basic outfit):
<ul>
<li>below -20: Thick down jacket+sweatshirt/hoodie/sweater+winter hat+gloves+boots+insulated pants</li>
<li>-20..-10: Down jacket+sweatshirt/hoodie/sweater+winter hat+gloves+boots+insulated pants</li>
<li>-10..0: Down jacket+sweatshirt/hoodie/sweater+hat+gloves+boots+pants</li>
<li>0..+10: Jacket+sweatshirt/hoodie/sweater+pants+footwear</li>
<li>+10..+15: Sweatshirt/hoodie/sweater+pants+footwear</li>
<li>+15..+20: Pants/shorts+shirt/t-shirt+footwear</li>
<li>above +20: Shorts+t-shirt+sandals</li>
</ul>

<b>Clouds</b> (additional):
Outfit according to the temperature+:
<ul>
<li>cloudy (t>0): optional/no headwear</li>
<li>sunny (t>0): headwear+sunglasses</li>
<li>sunny (t>15): light headwear+sunglasses</li>
</ul>


<b>Wind</b> (additional):
Outfit according to the temperature+:
<ul>
<li>light wind(3.4-5.4 m/s): light windjacket</li>
<li>moderate wind(5.5-7.9 m/s): windjacket</li>
<li>strong wind(>8 m/s): windjacket+fleece/sweatshirt</li>
</ul>

<b>Rain</b> (additional):
Outfit according to the temperature+:
<ul>
<li>rain: Rainjacket/umbrella</li>
</ul>

<b>UV index</b> (additional):
Outfit according to the temperature+:
<ul>
<li>high (>3): Sunscreen </li>
</ul>

<h2>The further development goals:</h2>

-The user can send the period (within the next 5 days due to free API key limitations) he’s going to stay in the chosen area and the bot will send back the appropriate outfit for every day.

-The user can send up to three cities with period of stay in each of them (within the next 5 days due to free API key limitations) (e.g. Berlin 4-6; London 6-7; Paris 7-8).

-The user can choose the specific time in the City  for the certain date (within the next 5 days due to free API key limitations) he needs to choose the outfit for (e.g Berlin 28.05 12:00).

<h2>Source used:</h2>
OWM-JAPIs: https://github.com/iamashks/OWM-JAPIs

