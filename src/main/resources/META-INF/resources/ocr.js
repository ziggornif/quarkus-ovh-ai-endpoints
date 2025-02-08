window.onload = async function () {
  const form = document.getElementById('ocr-form');
  const resultContainer = document.getElementById('resultContainer');
  const imageContainer = document.getElementById('uploadedImage');
  if (!form || !resultContainer) {
      console.error("Form or result container not found");
      return;
  }

  const fileInput = form.querySelector("input[type='file']");
      if (!fileInput) {
          console.error("File input not found");
          return;
      }

      fileInput.addEventListener("change", (event) => {
          const file = event.target.files[0];
          if (file) {
              const reader = new FileReader();
              reader.onload = (e) => {
                  imageContainer.src = e.target.result;
                  imageContainer.style.display = "block";
              };
              reader.readAsDataURL(file);
          }
      });

  form.addEventListener("submit", async (event) => {
      event.preventDefault();

      const formData = new FormData(form);

      try {
          const response = await fetch('/ai-assistant/ocr', {
              method: "POST",
              body: formData,
          });

          if (!response.ok) {
              throw new Error(`HTTP error! Status: ${response.status}`);
          }

          const result = await response.text();
          resultContainer.textContent =  result;
      } catch (error) {
          resultContainer.textContent = `Error: ${error.message}`;
          console.error("Error uploading file", error);
      }
  });
};