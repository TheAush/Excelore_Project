@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @GetMapping("/doctor")
    public ResponseEntity<String> suggestDoctor(@RequestParam Long patientId) {
        return ResponseEntity.ok(suggestionService.suggestDoctor(patientId));
    }
}
