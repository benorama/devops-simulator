/*global module:false*/
module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        // Metadata.
        pkg: grunt.file.readJSON('package.json'),
        // Task configuration.
        less: {
            bootstrap: {
                files: {
                    'grails-app/assets/stylesheets/bootstrap.css': 'src/less/bootstrap.less'
                }
            },
            'font-awesome': {
                files: {
                    'grails-app/assets/stylesheets/font-awesome.css': 'src/less/font-awesome.less'
                }
            }

        },
        watch: {
            options: {
                livereload: true
            },
            css: {
                files: ['src/less/*.less'],
                tasks: ['less']
            }
        }
    });

    // These plugins provide necessary tasks.
    //grunt.loadNpmTasks('grunt-contrib-concat');
    //grunt.loadNpmTasks('grunt-contrib-uglify');
    //grunt.loadNpmTasks('grunt-contrib-qunit');
    //grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-watch');

    // Default task.
    grunt.registerTask('default', ['watch']);

};
