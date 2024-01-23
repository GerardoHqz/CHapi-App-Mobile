particlesJS("BubbleID",
    {
        "particles": {
            "number": {
                "value": 50
            },
            "color": {
                "value": ['#78FBF7', '#E37222','#077889','#79CDD3']
            },
            "opacity": {
                "value": 0.9,
                "random": true,
                "anim": {
                    "enable": true,
                    "speed": 0.4,
                    "opacity_min": 0.6,
                    "sync": false
                }
            },

            "shape": {
                "type": "circle",
                "stroke": {
                    "width": 0,
                    "color": '#fff'
                }

            },
            "size": {
                "value": 60,
                "random": true
            },
            "line_linked": {
                "enable": false
            },
            "move": {
                "enable": true,
                "speed": 0.6,
                "direction": "none",
                "straight": false
            }
        },
        "interactivity": {

            "modes": {
                "push": {
                    "particles_nb": 12
                }
            },
            "move": {
                "enable": true,
                "speed": 20,
                "random": true,
                "direction": "bottom",
                "bounce": true,
                "out_mode": "out",
                "attract": {
                    "enable": true,
                    "rotateX": 10,
                    "rotateY": 10
                }
            },
            "detect_on": "canvas",
            "events": {
                "onhover": {
                    "enable": true,
                    "mode": "repulse"
                },
                "onclick": {
                    "enable": true,
                    "mode": "push"
                },
                "resize": true
            }

        }
    }
);