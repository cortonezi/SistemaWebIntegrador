const carousel = document.querySelector('.carousel-container');
const prevButton = document.querySelector('.prev-btn');
const nextButton = document.querySelector('.next-btn');

let currentIndex = 0; // Índice da imagem atual
const totalItems = document.querySelectorAll('.carousel-item').length; // Total de imagens

// Função para atualizar a posição do carrossel
function updateCarousel() {
    const offset = -currentIndex * 100; // Move o carrossel
    carousel.style.transform = `translateX(${offset}%)`;
}

// Evento para o botão "Anterior"
prevButton.addEventListener('click', () => {
    currentIndex = (currentIndex > 0) ? currentIndex - 1 : totalItems - 1; // Volta para a imagem anterior
    updateCarousel();
});

// Evento para o botão "Próximo"
nextButton.addEventListener('click', () => {
    currentIndex = (currentIndex < totalItems - 1) ? currentIndex + 1 : 0; // Avança para a próxima imagem
    updateCarousel();
});
