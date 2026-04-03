(() => {
  function wordCount(text) {
    const m = (text || "").trim().match(/[A-Za-zÀ-ÖØ-öø-ÿ0-9]+/g);
    return m ? m.length : 0;
  }

  function isValidHttpUrl(value) {
    try {
      const u = new URL(value);
      return u.protocol === "http:" || u.protocol === "https:";
    } catch {
      return false;
    }
  }

  function setFieldError(field, message) {
    field.setCustomValidity(message);
    field.classList.add("field-invalid");
    field.setAttribute("aria-invalid", "true");
  }

  function clearFieldError(field) {
    field.setCustomValidity("");
    field.classList.remove("field-invalid");
    field.removeAttribute("aria-invalid");
  }

  function validateForm(form) {
    const titulo = form.querySelector("#titulo");
    const director = form.querySelector("#director");
    const anyo = form.querySelector("#anyo");
    const imgUrl = form.querySelector("#imgUrl");
    const videoUrl = form.querySelector("#videoUrl");
    const descripcion = form.querySelector("#descripcion");
    const genero = form.querySelector("#genero");

    const fields = [titulo, director, anyo, imgUrl, videoUrl, descripcion, genero].filter(Boolean);
    fields.forEach(clearFieldError);

    let ok = true;

    if (!titulo || !titulo.value.trim()) {
      ok = false;
      if (titulo) setFieldError(titulo, "El titulo es obligatorio.");
    }

    if (!director || !director.value.trim()) {
      ok = false;
      if (director) setFieldError(director, "El director es obligatorio.");
    }

    if (!anyo || !anyo.value.trim()) {
      ok = false;
      if (anyo) setFieldError(anyo, "El ano es obligatorio.");
    } else {
      const year = Number(anyo.value);
      if (!Number.isFinite(year) || !Number.isInteger(year) || year < 1888 || year > 2100) {
        ok = false;
        setFieldError(anyo, "El ano debe ser un numero entre 1888 y 2100.");
      }
    }

    if (!imgUrl || !imgUrl.value.trim()) {
      ok = false;
      if (imgUrl) setFieldError(imgUrl, "La URL de imagen es obligatoria.");
    } else if (!isValidHttpUrl(imgUrl.value.trim())) {
      ok = false;
      setFieldError(imgUrl, "La URL de imagen debe ser valida (http/https).");
    }

    if (!videoUrl || !videoUrl.value.trim()) {
      ok = false;
      if (videoUrl) setFieldError(videoUrl, "La URL del video es obligatoria.");
    } else if (!isValidHttpUrl(videoUrl.value.trim())) {
      ok = false;
      setFieldError(videoUrl, "La URL del video debe ser valida (http/https).");
    }

    if (!descripcion || !descripcion.value.trim()) {
      ok = false;
      if (descripcion) setFieldError(descripcion, "La descripcion es obligatoria.");
    } else {
      const wc = wordCount(descripcion.value);
      if (wc < 20) {
        ok = false;
        setFieldError(descripcion, "La descripcion debe tener al menos 20 palabras.");
      }
    }

    if (!genero || !genero.value) {
      ok = false;
      if (genero) setFieldError(genero, "Selecciona un genero.");
    }

    // Let the browser show the first error bubble.
    if (!ok) form.reportValidity();
    return ok;
  }

  window.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("#createMovieForm");
    if (!form) return;

    form.addEventListener("submit", (e) => {
      if (!validateForm(form)) e.preventDefault();
    });

    // Validate as the user types/changes to remove errors quickly.
    const liveFields = form.querySelectorAll("input, textarea, select");
    liveFields.forEach((el) => {
      el.addEventListener("input", () => {
        el.setCustomValidity("");
        el.classList.remove("field-invalid");
        el.removeAttribute("aria-invalid");
      });
      el.addEventListener("change", () => {
        el.setCustomValidity("");
        el.classList.remove("field-invalid");
        el.removeAttribute("aria-invalid");
      });
    });
  });
})();

