async function postPrompt(prompt, negative_prompt) {
  const query = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      prompt,
      negative_prompt,
    }),
  };

  const resp = await fetch('/ai-assistant/generate-image', query);

  if (!resp.ok) {
    throw new Error('Network response was not ok');
  }

  const image = await resp.blob();

  return image;
}

function addLoader(element) {
  const loader = document.createElement('div');
  loader.id = 'loader';
  loader.setAttribute('aria-busy', 'true');
  loader.classList.add('loader-container');
  const loaderText = document.createElement('div');
  loaderText.textContent = 'Generating image...';
  loader.appendChild(loaderText);
  element.appendChild(loader);
}

function removeLoader(element) {
  const child = document.getElementById('loader');
  element.removeChild(child);
}

window.onload = async function () {
  const promptForm = document.getElementById('prompt-form');

  let userSession;
  promptForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    document.activeElement.blur();
    const promptElem = document.getElementById('prompt');
    const promptMessage = promptElem.value;
    const negPromptElem = document.getElementById('negative_prompt');
    const negPromptMessage = negPromptElem.value;
    const messagesDiv = document.getElementById('image');
    addLoader(messagesDiv);

//    const authorPrompt = document.createElement('p');
//    const robotPrompt = document.createElement('p');
//
//    const userAvatar = document.createElement('img');
//    userAvatar.src = 'assets/img/you_avatar.png';
//    userAvatar.alt = 'user avatar';
//    authorPrompt.appendChild(userAvatar);
//    const userPrompt = document.createTextNode(` ${promptMessage}`);
//    authorPrompt.appendChild(userPrompt);
//    messagesDiv.appendChild(authorPrompt);
//    addLoader(messagesDiv);
//    promptElem.value = null;
    promptElem.disabled = true;
    negPromptElem.disabled = true;



    const image = await postPrompt(promptMessage, negPromptMessage);
    const imageUrl = URL.createObjectURL(image);
    const img = document.createElement('img');
    img.src = imageUrl;
    img.classList.add('reduced-image');
    messagesDiv.appendChild(img);
//    userSession = sessionId;

//    removeLoader(messagesDiv);
//    robotPrompt.innerHTML = '';
//    messagesDiv.appendChild(robotPrompt);
//    const decoder = new TextDecoder('utf-8');
//    // Read chunks of data from the stream
//    let text = '![avatar](assets/img/avatar.png) ';
//    const readStream = () => {
//      reader
//        .read()
//        .then(({ done, value }) => {
//          if (done) {
//            return;
//          }
//          const chunkString = decoder.decode(value);
//          text += chunkString;
//          robotPrompt.innerHTML = markdownIt().render(text);
//          promptElem.scrollIntoView();
//          readStream();
//        })
//        .catch((error) => {
//          console.error('Error reading stream:', error);
//        });
//    };
//    readStream();
    removeLoader(messagesDiv)
    promptElem.disabled = false;
    negPromptElem.disabled = false;
    messagesDiv.appendChild(document.createElement('hr'));
    messagesDiv.scrollIntoView();
  });
};